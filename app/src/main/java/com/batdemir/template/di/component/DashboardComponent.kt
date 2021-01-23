package com.batdemir.template.di.component

import com.batdemir.template.ui.view.dashboard.DashboardFragment
import dagger.Subcomponent

@Subcomponent
interface DashboardComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DashboardComponent
    }

    fun inject(dashboardFragment: DashboardFragment)
}