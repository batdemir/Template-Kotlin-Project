package com.batdemir.template.di.component

import com.batdemir.template.ui.view.home.HomeFragment
import dagger.Subcomponent

@Subcomponent
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(homeFragment: HomeFragment)
}