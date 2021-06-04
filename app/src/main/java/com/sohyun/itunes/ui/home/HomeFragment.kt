package com.sohyun.itunes.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sohyun.itunes.R
import com.sohyun.itunes.databinding.FragmentHomeBinding
import com.sohyun.itunes.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = homeViewModel
        binding.lifecycleOwner = this@HomeFragment
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}