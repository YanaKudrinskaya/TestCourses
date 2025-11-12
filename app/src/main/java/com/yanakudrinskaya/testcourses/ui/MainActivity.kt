package com.yanakudrinskaya.testcourses.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yanakudrinskaya.core.navigation.NavigationContract
import com.yanakudrinskaya.core.navigation.NavigationDestination
import com.yanakudrinskaya.testcourses.R
import com.yanakudrinskaya.testcourses.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    @Inject
    lateinit var navigator: NavigationContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        navigator.setNavController(navController)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.coursesFragment -> navigator.navigateTo(NavigationDestination.Home)
                R.id.favoritesFragment -> navigator.navigateTo(NavigationDestination.Favorites)
                R.id.accountFragment -> navigator.navigateTo(NavigationDestination.Account)
            }
            true
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val destinationId = destination.id
            val shouldShowBottomNav = navigator.shouldShowBottomNav(destinationId)
            viewModel.setNavigationVisible(shouldShowBottomNav)
        }
        viewModel.getNavigationEvents().observe(this) { isVisible ->
            bottomNavigationView.isVisible = isVisible
        }
    }
}