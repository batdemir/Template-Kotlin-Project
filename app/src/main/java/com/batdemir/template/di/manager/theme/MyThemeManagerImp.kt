package com.batdemir.template.di.manager.theme

import androidx.appcompat.app.AppCompatDelegate
import com.batdemir.template.R
import com.batdemir.template.di.manager.resource.MyResourceManager
import com.batdemir.template.di.manager.storage.MyStorageManager
import javax.inject.Inject

class MyThemeManagerImp @Inject constructor(
    private val myResourceManager: MyResourceManager,
    private val storageManager: MyStorageManager
) : MyThemeManager {
    override fun setDefaultTheme() {
        val themeMode =
            storageManager.getInt(myResourceManager.getResources().getString(R.string.KEY_THEME))
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
        storageManager.setInt(
            myResourceManager.getContext().getString(R.string.KEY_THEME),
            themeMode
        )
    }
}