package com.yanakudrinskaya.home.ui.adapter

import com.yanakudrinskaya.core.adapter.BaseAdapter
import com.yanakudrinskaya.domain.models.Course

class HomeCoursesAdapter(
    onItemClick: ((Course) -> Unit)? = null,
    private val onLikeClick: (Course) -> Unit = {}
) : BaseAdapter<Course>(
    viewHolderFactory = { parent ->
        HomeCourseViewHolder.create(parent, onLikeClick)
    },
    onItemClick = onItemClick
)