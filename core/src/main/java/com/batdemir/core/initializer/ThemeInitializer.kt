package com.batdemir.core.initializer

import android.content.Context
import androidx.startup.Initializer
import com.batdemir.core.manager.theme.MyThemeManager
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
