package com.sohyun.itunes.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sohyun.itunes.BR

class DatBindingAdapter<ITEM, VH> constructor(
    private val onItemClickListener: ((view: View, position: Int, item: ITEM) -> Unit)? = null,
    private val variableId: Int? = BR.item,
): BaseRecyclerviewAdapter<ITEM, BaseViewHolder<ITEM>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ITEM> {
        return BaseViewHolder<ITEM>(
            DataBindingUtil.inflate(
                LayoutInflater.from(
                    parent.context
                ), viewType, parent, false),
            variableId
        ).apply {
            dataBinding.root.setOnClickListener {
                try {
                    onItemClickListener?.invoke(it, bindingAdapterPosition, getItem(bindingAdapterPosition))
                } catch (e: IndexOutOfBoundsException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ITEM>, position: Int) {
        holder.onBind(getItem(position))
    }
}