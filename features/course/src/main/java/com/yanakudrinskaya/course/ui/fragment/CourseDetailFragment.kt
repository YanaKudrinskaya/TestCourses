package com.yanakudrinskaya.course.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.yanakudrinskaya.core.navigation.NavigationVisibilityController
import com.yanakudrinskaya.core.utils.formatDate
import com.yanakudrinskaya.course.databinding.FragmentCourseDetailBinding
import com.yanakudrinskaya.course.ui.models.CourseScreenState
import com.yanakudrinskaya.course.ui.view_model.CourseDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.getValue

class CourseDetailFragment : Fragment() {

    private var _binding: FragmentCourseDetailBinding? = null
    private val binding get() = _binding!!

    private val courseId: Long by lazy {
        arguments?.getLong("courseId", 0L) ?: 0L
    }

    private val viewModel by viewModel<CourseDetailViewModel> {
        parametersOf(courseId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCourseDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? NavigationVisibilityController)?.setNavigationVisibility(false)

        setupClickListeners()
        setupObservers()
    }

    private fun setupClickListeners() {
        binding.fabBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.fabBookmark.setOnClickListener {
            viewModel.onFavoriteClicked()
        }
    }

    private fun setupObservers() {
        viewModel.getScreenStateLiveData().observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is CourseScreenState.Content -> setupContent(screenState)
                is CourseScreenState.Error -> {}
            }
        }
    }

    private fun setupContent(content: CourseScreenState.Content) {
        binding.apply {
            fabBookmark.isSelected = content.course.hasLike
            tvRating.text = content.course.rate
            tvDate.text = formatDate(content.course.startDate)
            tvTitle.text = content.course.title
            tvDescription.text = content.course.text
        }
    }

    override fun onDestroyView() {
        (activity as? NavigationVisibilityController)?.setNavigationVisibility(true)
        super.onDestroyView()
        _binding = null
    }
}