package com.yanakudrinskaya.core.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.yanakudrinskaya.core.R
import com.yanakudrinskaya.core.databinding.CourseCardBinding
import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.core.utils.formatDate
import com.yanakudrinskaya.core.utils.formatPrice
import com.yanakudrinskaya.core.utils.toPx

class CoursesViewHolder(
    private val binding: CourseCardBinding,
    private val onItemClick: ((Course) -> Unit)? = null,
    private val onLikeClick: ((Course) -> Unit)? = null
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(course: Course) {
        binding.apply {
            Glide.with(root)
                .load(R.drawable.img_placeholder)
                .transform(
                    CenterCrop(),
                    RoundedCorners(binding.root.context.toPx(12)))
                .into(binding.ivCover)

            tvTitle.text = course.title
            tvDescription.text = course.text
            tvPrice.text = formatPrice(course.price, root.context)
            tvRating.text = course.rate
            tvDate.text = formatDate(course.startDate)
            ivBookmark.isSelected = course.hasLike

            root.setOnClickListener {
                onItemClick?.invoke(course)
            }
            ivBookmark.setOnClickListener {
                onLikeClick?.invoke(course)
            }
        }
    }
}