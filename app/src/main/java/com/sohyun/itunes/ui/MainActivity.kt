package com.sohyun.itunes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sohyun.itunes.R
import com.sohyun.itunes.databinding.ActivityMainBinding
import com.sohyun.itunes.ui.favorite.FavoriteFragment
import com.sohyun.itunes.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        binding.bottomNavigation.setOnNavigationItemReselectedListener { item ->
//            val instance = when (item.itemId) {
//                R.id.nav_home -> HomeFragment.newInstance()
//                R.id.nav_favorite -> FavoriteFragment.newInstance()
//                else -> HomeFragment.newInstance()
//            }
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.main_framelayout, instance)
//                .commitNow()
//        }

        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).let {
            binding.bottomNavigation.setupWithNavController(it.navController)
            binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
                NavigationUI.onNavDestinationSelected(item, it.navController)
            }
        }
    }
}