package com.yanakudrinskaya.core.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    private val viewHolderFactory: (parent: ViewGroup) -> BaseViewHolder<T>,
    private var onItemClick: ((T) -> Unit)? = null
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return viewHolderFactory(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

