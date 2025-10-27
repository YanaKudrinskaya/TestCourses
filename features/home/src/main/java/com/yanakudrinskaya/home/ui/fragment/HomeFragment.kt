package com.yanakudrinskaya.home.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.yanakudrinskaya.core.R
import com.yanakudrinskaya.core.adapter.CoursesAdapter
import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.core.navigation.NavigationContract
import com.yanakudrinskaya.core.ui.decorator.ItemOffsetDecoration
import com.yanakudrinskaya.home.databinding.FragmentHomeBinding
import com.yanakudrinskaya.home.ui.view_model.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()

    private var _coursesAdapter: CoursesAdapter? = null
    private val coursesAdapter get() = _coursesAdapter!!
    private val navigationContract: NavigationContract?
        get() = activity as? NavigationContract

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        _coursesAdapter = CoursesAdapter(
            onItemClick = { course ->
                onCourseClick(course)
            },
            onLikeClick = { course ->
                onLikeClick(course)
            }
        )

        binding.rvCourses.apply {
            adapter = coursesAdapter
            val decoration =
                ItemOffsetDecoration(resources.getDimensionPixelSize(R.dimen.guideline))
            addItemDecoration(decoration)
        }

    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCoursesState().collect { courses ->
                coursesAdapter.updateData(courses)
            }
        }
    }

    private fun setupClickListeners() {
        binding.tvSort.setOnClickListener {
            viewModel.toggleSort()
        }
    }

    private fun onCourseClick(course: Course) {
        navigationContract?.navigateToCourseDetail(course.id)
    }

    private fun onLikeClick(course: Course) {
        viewModel.toggleLike(course)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}