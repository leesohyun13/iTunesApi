package com.sohyun.itunes.ui.base

import com.sohyun.itunes.BR
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding, M: BaseViewModel>(private val resId: Int): Fragment() {
    protected abstract val viewModel: M
    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<B>(inflater, resId, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewmodel, viewModel)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // TODO binding null 처리 추가
    }
}