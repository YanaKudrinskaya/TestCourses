package com.yanakudrinskaya.course.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yanakudrinskaya.core.navigation.NavigationContract
import com.yanakudrinskaya.core.utils.formatDate
import com.yanakudrinskaya.course.databinding.FragmentCourseDetailBinding
import com.yanakudrinskaya.course.ui.models.CourseScreenState
import com.yanakudrinskaya.course.ui.view_model.CourseDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class CourseDetailFragment : Fragment() {

    private var _binding: FragmentCourseDetailBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var navigator: NavigationContract
    private val viewModel: CourseDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        setupObservers()
    }

    private fun setupClickListeners() {
        binding.fabBack.setOnClickListener {
            navigator.navigateBack()
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
        super.onDestroyView()
        _binding = null
    }
}