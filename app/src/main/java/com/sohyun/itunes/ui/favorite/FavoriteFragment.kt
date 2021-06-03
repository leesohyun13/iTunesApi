package com.sohyun.itunes.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sohyun.itunes.databinding.FavoriteFragmentBinding

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private lateinit var binding: FavoriteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoriteFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = favoriteViewModel
            lifecycleOwner = this@FavoriteFragment
        }

        return binding.root
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}