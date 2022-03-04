package com.batdemir.core.manager.theme

import androidx.appcompat.app.AppCompatDelegate
import com.batdemir.core.R
import com.batdemir.core.manager.resource.MyResourceManager
import com.batdemir.core.manager.storage.MyStorageManager
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
        return when (themeMode) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> {
                Themes.values()
                    .first { x -> x.themeValue == AppCompatDelegate.getDefaultNightMode() }
            }
            AppCompatDelegate.MODE_NIGHT_UNSPECIFIED -> {
                Themes.FLOW_SYSTEM
            }
            else -> {
                Themes.values()
                    .first { x -> x.themeValue == themeMode }
            }
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