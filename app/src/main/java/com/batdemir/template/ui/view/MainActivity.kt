package com.batdemir.template.ui.view

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.batdemir.template.R
import com.batdemir.template.app.MyApplication
import com.batdemir.template.databinding.ActivityMainBinding
import com.batdemir.template.di.component.GithubComponent
import com.batdemir.template.di.component.HomeComponent
import com.batdemir.template.di.component.SettingsComponent
import com.batdemir.template.di.component.StackOverFlowComponent
import com.batdemir.template.ui.base.view.BaseActivity

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    lateinit var homeComponent: HomeComponent
    lateinit var stackOverFlowComponent: StackOverFlowComponent
    lateinit var githubComponent: GithubComponent
    lateinit var settingsComponent: SettingsComponent

    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.navigation_host_fragment).navigateUp()

    override fun onBackPressed() {
        if (!onSupportNavigateUp())
            super.onBackPressed()
    }

    override fun inject() {
        val applicationComponent = (application as MyApplication).applicationComponent
        applicationComponent.inject(this)
        homeComponent = applicationComponent.homeComponent().create()
        stackOverFlowComponent = applicationComponent.stackOverFlowComponent().create()
        githubComponent = applicationComponent.githubComponent().create()
        settingsComponent = applicationComponent.settingsComponent().create()
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        val toolbar = getBinding().toolbar
        val bottomNavigationView = getBinding().bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun setupListener() {
        //("Not yet implemented")
    }
}