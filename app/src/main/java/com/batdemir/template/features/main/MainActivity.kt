package com.batdemir.template.features.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.batdemir.core.core.view.BaseActivity
import com.batdemir.core.extensions.move
import com.batdemir.template.R
import com.batdemir.template.databinding.ActivityMainBinding
import com.batdemir.abtest.scenario_v1.usage_1.view.MainActivityV1
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()
    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.navigation_host_fragment).navigateUp()

    override fun onResume() {
        super.onResume()
        move(com.batdemir.abtest.scenario_v1.usage_1.view.MainActivityV1::class.java, isKeepHistory = true)
    }

    override fun onBackPressed() {
        if (!onSupportNavigateUp())
            super.onBackPressed()
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        setupViewModel(viewModel)
        val toolbar = getBinding().toolbar
        val bottomNavigationView = getBinding().bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, _, _ ->
            dismissProgress()
        }
    }
}
