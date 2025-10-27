package com.yanakudrinskaya.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yanakudrinskaya.core.databinding.CourseCardBinding
import com.yanakudrinskaya.core.models.Course

class CoursesAdapter(
    private var onItemClick: ((Course) -> Unit)? = null,
    private var onLikeClick: ((Course) -> Unit)? = null
) : RecyclerView.Adapter<CoursesViewHolder>() {
    var coursesList = mutableListOf<Course>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        val binding = CourseCardBinding.inflate(layoutInspector, parent, false)
        return CoursesViewHolder(binding, onItemClick, onLikeClick)
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind(coursesList[position])
    }

    fun updateData(newCourses: List<Course>) {
        coursesList.clear()
        coursesList.addAll(newCourses)
        notifyDataSetChanged()
    }
}
