package com.batdemir.template.di.initializer

import com.batdemir.template.app.MyApplication
import com.batdemir.template.di.manager.theme.MyThemeManager
import javax.inject.Inject

class ThemeInitializer @Inject constructor(
    private val myThemeManager: MyThemeManager
) : Initializer {
    override fun initialize(application: MyApplication) {
        myThemeManager.setDefaultTheme()
    }
}