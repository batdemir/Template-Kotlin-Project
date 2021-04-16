package com.batdemir.template.ui.view

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.batdemir.template.R
import com.batdemir.template.app.MyApplication
import com.batdemir.template.databinding.ActivityMainBinding
import com.batdemir.template.di.component.GithubComponent
import com.batdemir.template.di.component.HomeComponent
import com.batdemir.template.di.component.SettingsComponent
import com.batdemir.template.di.component.StackOverFlowComponent
import com.batdemir.template.ui.base.view.BaseActivity
import com.batdemir.template.utils.setupWithNavController

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    lateinit var homeComponent: HomeComponent
    lateinit var stackOverFlowComponent: StackOverFlowComponent
    lateinit var githubComponent: GithubComponent
    lateinit var settingsComponent: SettingsComponent
    private var currentNavController: LiveData<NavController>? = null

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean = currentNavController?.value?.navigateUp() ?: false

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
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun setupData() {
        //("Not yet implemented")
    }

    override fun setupListener() {
        //("Not yet implemented")
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        setSupportActionBar(getBinding().toolbar)
        val navGraphIds = listOf(
            R.navigation.home_navigation,
            R.navigation.stack_over_flow_navigation,
            R.navigation.github_navigation
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = getBinding().bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = getBinding().navigationHostFragment.id,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(
            this,
            { navController -> setupActionBarWithNavController(navController) },
        )
        currentNavController = controller
    }
}