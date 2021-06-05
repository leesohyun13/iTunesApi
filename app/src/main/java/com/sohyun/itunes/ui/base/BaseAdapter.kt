package com.sohyun.itunes.ui.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, H : BaseViewHolder<T>>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, H>(diffCallback) {
    val items = mutableListOf<T>()

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bind(items[position])
    }

    open fun addAll(newItems: List<T>) {
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }

    open fun updateItem(item: T) {
        val pos = this.items.indexOf(item)
        this.items[pos] = item
        notifyItemChanged(pos)
    }

    open fun removeItem(item: T) {
        val pos = this.items.indexOf(item)
        this.items.remove(item)
        notifyItemRemoved(pos)
    }

    override fun getItem(position: Int): T {
        return super.getItem(position)
    }
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}