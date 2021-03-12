package com.batdemir.template.ui.view.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.batdemir.template.BuildConfig
import com.batdemir.template.di.manager.language.Languages
import com.batdemir.template.di.manager.language.MyLanguageManager
import com.batdemir.template.di.manager.theme.MyThemeManager
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val myLanguageManager: MyLanguageManager,
    private val myThemeManager: MyThemeManager,
) : ViewModel() {
    fun changeLanguage(newValue: String) {
        myLanguageManager.changeLanguage(
            Languages.values().first { x -> x.languageName == newValue })
    }

    fun changeTheme(newValue: Boolean) {
        myThemeManager.changeTheme(if (newValue) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
    }

    fun getVersionName(): String = BuildConfig.VERSION_NAME
}