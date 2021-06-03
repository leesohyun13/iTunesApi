package com.sohyun.itunes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sohyun.itunes.R
import com.sohyun.itunes.databinding.ActivityMainBinding
import com.sohyun.itunes.ui.favorite.FavoriteFragment
import com.sohyun.itunes.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bottomNavigation.setOnNavigationItemReselectedListener { item ->
            val instance = when (item.itemId) {
                R.id.nav_home -> HomeFragment.newInstance()
                R.id.nav_favorite -> FavoriteFragment.newInstance()
                else -> HomeFragment.newInstance()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_framelayout, instance)
                .commitNow()
        }
    }
}