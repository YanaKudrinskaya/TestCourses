package com.yanakudrinskaya.favorites.ui.adapter

import com.yanakudrinskaya.core.adapter.BaseAdapter
import com.yanakudrinskaya.domain.models.Course

class FavoritesCoursesAdapter(
    onItemClick: ((Course) -> Unit)? = null,
    private val onLikeClick: (Course) -> Unit = {}
) : BaseAdapter<Course>(
    viewHolderFactory = { parent ->
        FavoritesCourseViewHolder.create(parent, onLikeClick)
    },
    onItemClick = onItemClick
)