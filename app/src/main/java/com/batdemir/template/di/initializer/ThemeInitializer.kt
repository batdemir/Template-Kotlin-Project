package com.batdemir.template.di.initializer

import android.content.Context
import androidx.startup.Initializer
import com.batdemir.template.di.manager.theme.MyThemeManager
import javax.inject.Inject

class ThemeInitializer : Initializer<MyThemeManager> {
    @Inject
    lateinit var myLanguageManager: MyThemeManager

    override fun create(context: Context): MyThemeManager {
        InitializerEntryPoint.resolve(context).inject(this)
        myLanguageManager.setDefaultTheme()
        return myLanguageManager
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}