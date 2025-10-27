package com.yanakudrinskaya.main.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yanakudrinskaya.core.navigation.NavigationContract
import com.yanakudrinskaya.core.navigation.NavigationVisibilityController
import com.yanakudrinskaya.main.R
import com.yanakudrinskaya.main.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), NavigationVisibilityController, NavigationContract {
    private lateinit var binding: ActivityMainBinding
    internal val viewModel by viewModel<MainViewModel>()

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

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        viewModel.getNavigationEvents().observe(this) { isVisible ->
            bottomNavigationView.isVisible = isVisible
        }
    }

    override fun setNavigationVisibility(visible: Boolean) {
        viewModel.setNavigationVisible(visible)
    }

    override fun navigateToHome() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_login_to_main)
    }

    override fun navigateToCourseDetail(courseId: Long) {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        val bundle = Bundle().apply {
            putLong(COURSE_ID, courseId)
        }
        navController.navigate(R.id.courseDetailFragment, bundle)
    }

    companion object {
        const val COURSE_ID = "courseId"
    }
}