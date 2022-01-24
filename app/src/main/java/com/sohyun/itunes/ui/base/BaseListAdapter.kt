package com.sohyun.itunes.ui.base

import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<ITEM> : ListAdapter<ITEM, BaseViewHolder<ITEM>>(BaseDiffCallback<ITEM>())