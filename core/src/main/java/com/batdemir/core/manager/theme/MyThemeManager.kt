package com.batdemir.core.manager.theme

interface MyThemeManager {
    fun setDefaultTheme()
    fun getCurrentTheme(): Themes
    fun changeTheme(themeMode: Themes)
}
