package com.sohyun.itunes.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sohyun.itunes.R
import com.sohyun.itunes.databinding.FragmentFavoriteBinding
import com.sohyun.itunes.ui.base.BaseFragment

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite)  {
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = favoriteViewModel
        binding.lifecycleOwner = this@FavoriteFragment
    }


    companion object {
        fun newInstance() = FavoriteFragment()
    }
}