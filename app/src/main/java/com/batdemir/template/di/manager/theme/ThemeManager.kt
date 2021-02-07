package com.batdemir.template.di.manager.theme

import androidx.appcompat.app.AppCompatDelegate
import com.batdemir.template.R
import com.batdemir.template.di.manager.resource.IResourceManager
import com.batdemir.template.di.manager.storage.IStorageManager
import javax.inject.Inject

class ThemeManager @Inject constructor(
    private val resourceManager: IResourceManager,
    private val storageManager: IStorageManager
) : IThemeManager {
    override fun setDefaultTheme() {
        val themeMode =
            storageManager.getInt(resourceManager.getResources().getString(R.string.KEY_THEME))
        if (themeMode == -1) changeTheme(AppCompatDelegate.MODE_NIGHT_NO)
        else changeTheme(themeMode)
    }

    override fun changeTheme(themeMode: Int) {
        when (themeMode) {
            AppCompatDelegate.MODE_NIGHT_NO -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )
            AppCompatDelegate.MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        storageManager.setInt(resourceManager.getContext().getString(R.string.KEY_THEME), themeMode)
    }
}