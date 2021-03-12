package com.batdemir.template.ui.view

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.batdemir.template.R
import com.batdemir.template.app.MyApplication
import com.batdemir.template.databinding.ActivityMainBinding
import com.batdemir.template.di.component.DashboardComponent
import com.batdemir.template.di.component.HomeComponent
import com.batdemir.template.di.component.NotificationsComponent
import com.batdemir.template.di.component.SettingsComponent
import com.batdemir.template.ui.base.view.BaseActivity
import com.batdemir.template.utils.setupWithNavController
import com.google.android.material.progressindicator.LinearProgressIndicator
import javax.inject.Inject

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    @Inject
    lateinit var viewModel: MainViewModel
    lateinit var homeComponent: HomeComponent
    lateinit var dashboardComponent: DashboardComponent
    lateinit var notificationsComponent: NotificationsComponent
    lateinit var settingsComponent: SettingsComponent
    private var currentNavController: LiveData<NavController>? = null
    var progressBar: LinearProgressIndicator? = null

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
        dashboardComponent = applicationComponent.dashboardComponent().create()
        notificationsComponent = applicationComponent.notificationsComponent().create()
        settingsComponent = applicationComponent.settingsComponent().create()
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        progressBar = binding!!.progressBar
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
        setSupportActionBar(binding!!.toolbar)
        val navGraphIds = listOf(
            R.navigation.home_navigation,
            R.navigation.dashboard_navigation,
            R.navigation.notifications_navigation
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = binding!!.bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = binding!!.navigationHostFragment.id,
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