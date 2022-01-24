package com.sohyun.itunes.ui.base

import com.sohyun.itunes.BR
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<ITEM>(
    open val dataBinding: ViewDataBinding,
    private val variableId: Int? = BR.item,
) : RecyclerView.ViewHolder(dataBinding.root) {
    fun onBind(item: ITEM?) {
//        this.item = item
        variableId?.let {
            dataBinding.setVariable(it, item)
        }
        dataBinding.executePendingBindings()
    }
}