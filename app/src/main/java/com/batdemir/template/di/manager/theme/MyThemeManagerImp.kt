package com.batdemir.template.di.manager.theme

import androidx.appcompat.app.AppCompatDelegate
import com.batdemir.template.R
import com.batdemir.template.di.manager.resource.MyResourceManager
import com.batdemir.template.di.manager.storage.MyStorageManager
import javax.inject.Inject

class MyThemeManagerImp @Inject constructor(
    private val myResourceManager: MyResourceManager,
    private val myStorageManager: MyStorageManager
) : MyThemeManager {
    override fun setDefaultTheme() {
        changeTheme(getCurrentTheme())
    }

    override fun getCurrentTheme(): Themes {
        val themeMode =
            myStorageManager.getInt(
                myResourceManager.getResources()
                    .getString(R.string.KEY_THEME)
            )
        return if (themeMode == -1) {
            Themes.values()
                .first { x -> x.themeValue == AppCompatDelegate.getDefaultNightMode() }
        } else {
            Themes.values()
                .first { x -> x.themeValue == themeMode }
        }
    }

    override fun changeTheme(themeMode: Themes) {
        AppCompatDelegate.setDefaultNightMode(themeMode.themeValue)
        myStorageManager.setInt(
            myResourceManager.getContext()
                .getString(R.string.KEY_THEME),
            themeMode.themeValue
        )
    }
}