package com.batdemir.core.features.settings

import com.batdemir.core.BuildConfig
import com.batdemir.core.core.vm.BaseViewModel
import com.batdemir.core.manager.language.Languages
import com.batdemir.core.manager.language.MyLanguageManager
import com.batdemir.core.manager.resource.MyResourceManager
import com.batdemir.core.manager.theme.MyThemeManager
import com.batdemir.core.manager.theme.Themes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val myLanguageManager: MyLanguageManager,
    private val myThemeManager: MyThemeManager,
    private val myResourceManager: MyResourceManager
) : BaseViewModel() {
    fun changeLanguage(newValue: String) = myLanguageManager.changeLanguage(
        Languages.values()
            .first { x ->
                myResourceManager.getResources()
                    .getString(x.languageName) == newValue
            }
    )

    fun changeTheme(newValue: String) = myThemeManager.changeTheme(
        Themes.values()
            .first { x ->
                myResourceManager.getResources()
                    .getString(x.themeName) == newValue
            }
    )

    fun getVersionName(): String = BuildConfig.VERSION_NAME
    fun getLanguageResId(): Int = myLanguageManager.getCurrentLanguage().languageName
    fun getThemeResId(): Int = myThemeManager.getCurrentTheme().themeName
}
