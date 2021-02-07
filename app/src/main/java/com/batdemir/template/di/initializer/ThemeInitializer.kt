package com.batdemir.template.di.initializer

import com.batdemir.template.app.MyApplication
import com.batdemir.template.di.manager.theme.IThemeManager
import javax.inject.Inject

class ThemeInitializer @Inject constructor(
    private val themeManager: IThemeManager
) : Initializer {
    override fun initialize(application: MyApplication) {
        themeManager.setDefaultTheme()
    }

}