package com.batdemir.template.di.component

import com.batdemir.template.ui.view.settings.SettingsFragment
import dagger.Subcomponent

@Subcomponent
interface SettingsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsComponent
    }

    fun inject(settingsFragment: SettingsFragment)
}