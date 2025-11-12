package com.yanakudrinskaya.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.yanakudrinskaya.core.adapter.BaseViewHolder
import com.yanakudrinskaya.core.databinding.CourseCardBinding
import com.yanakudrinskaya.domain.models.Course
import com.yanakudrinskaya.core.utils.formatDate
import com.yanakudrinskaya.core.utils.formatPrice
import com.yanakudrinskaya.core.utils.toPx

class HomeCourseViewHolder(
    private val binding: CourseCardBinding,
    private val onLikeClick: (Course) -> Unit
) : BaseViewHolder<Course>(binding.root) {

    override fun bind(item: Course, onItemClick: ((Course) -> Unit)?) {
        binding.apply {
            tvTitle.text = item.title
            tvDescription.text = item.text
            tvPrice.text = formatPrice(item.price, root.context)
            tvRating.text = item.rate
            tvDate.text = formatDate(item.startDate)
            ivBookmark.isSelected = item.hasLike

            Glide.with(root)
                .load(com.yanakudrinskaya.core.R.drawable.img_placeholder)
                .transform(
                    com.bumptech.glide.load.resource.bitmap.CenterCrop(),
                    com.bumptech.glide.load.resource.bitmap.RoundedCorners(
                        root.context.toPx(12)
                    )
                )
                .into(ivCover)

            root.setOnClickListener {
                onItemClick?.invoke(item)
            }
            ivBookmark.setOnClickListener {
                onLikeClick(item)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onLikeClick: (Course) -> Unit
        ): HomeCourseViewHolder {
            val binding = CourseCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return HomeCourseViewHolder(binding, onLikeClick)
        }
    }
}