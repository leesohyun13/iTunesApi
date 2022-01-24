package com.sohyun.itunes.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerviewAdapter<ITEM, VH: RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    private val itemList = mutableListOf<ITEM>()

    fun getItem(position: Int): ITEM = itemList[position]

    fun getItemOrNull(position: Int): ITEM? = itemList.getOrNull(position)

    override fun getItemCount(): Int {
        return itemList.size
    }
}