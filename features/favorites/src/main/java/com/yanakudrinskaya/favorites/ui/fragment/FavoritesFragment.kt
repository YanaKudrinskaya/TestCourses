package com.yanakudrinskaya.favorites.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.yanakudrinskaya.core.adapter.CoursesAdapter
import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.core.navigation.NavigationContract
import com.yanakudrinskaya.core.ui.decorator.ItemOffsetDecoration
import com.yanakudrinskaya.favorites.databinding.FragmentFavoritesBinding
import com.yanakudrinskaya.favorites.ui.view_model.FavoriteViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<FavoriteViewModel>()

    private var _coursesAdapter: CoursesAdapter? = null
    private val coursesAdapter get() = _coursesAdapter!!

    private val navigationContract: NavigationContract?
        get() = activity as? NavigationContract

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
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
                ItemOffsetDecoration(resources.getDimensionPixelSize(com.yanakudrinskaya.core.R.dimen.guideline))
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