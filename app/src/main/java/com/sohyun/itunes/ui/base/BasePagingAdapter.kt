package com.sohyun.itunes.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sohyun.itunes.BR

abstract class BasePagingAdapter<ITEM: Any> constructor(
    diffCallback: DiffUtil.ItemCallback<ITEM>,
    private val variableId: Int = BR.item,
): PagingDataAdapter<ITEM, BaseViewHolder<ITEM>>(diffCallback) {

    var onViewItemClickListener: ((view: View, position: Int, item: ITEM?) -> Unit)? = null

    abstract fun getLayoutResType(position: Int): Int

    override fun getItemViewType(position: Int): Int {
        return getLayoutResType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ITEM> {
        return BaseViewHolder<ITEM>(
            DataBindingUtil.inflate(
                LayoutInflater.from(
                    parent.context
                ), viewType, parent, false
            ), variableId
        ).apply {
            dataBinding.root.setOnClickListener {
                try {
                    onViewItemClickListener?.invoke(dataBinding.root, bindingAdapterPosition, getItem(bindingAdapterPosition))
                } catch (e: IndexOutOfBoundsException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ITEM>, position: Int) {
        try {
//            holder.variableMap.apply(setVariableMap)
            holder.onBind(getItem(position))
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }
}