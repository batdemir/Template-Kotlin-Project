package com.batdemir.template.ui.view.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.batdemir.template.BuildConfig
import com.batdemir.template.di.manager.language.ILanguageManager
import com.batdemir.template.di.manager.language.Languages
import com.batdemir.template.di.manager.theme.IThemeManager
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val languageManager: ILanguageManager,
    private val themeManager: IThemeManager,
) : ViewModel() {
    fun changeLanguage(newValue: String) {
        languageManager.changeLanguage(Languages.values().first { x -> x.languageName == newValue })
    }

    fun changeTheme(newValue: Boolean) {
        themeManager.changeTheme(if (newValue) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
    }

    fun getVersionName(): String = BuildConfig.VERSION_NAME
}