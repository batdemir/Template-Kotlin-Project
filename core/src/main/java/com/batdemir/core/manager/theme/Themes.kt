package com.batdemir.core.manager.theme

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import com.batdemir.core.R

enum class Themes(
    @StringRes
    val themeName: Int,
    val themeValue: Int
) {
    UNSPECIFIED(
        R.string.theme_unspecified,
        AppCompatDelegate.MODE_NIGHT_UNSPECIFIED,
    ),
    FLOW_SYSTEM(
        R.string.theme_flow_system,
        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
    ),
    DARK(
        R.string.theme_dark,
        AppCompatDelegate.MODE_NIGHT_YES,
    ),
    LIGHT(
        R.string.theme_light,
        AppCompatDelegate.MODE_NIGHT_NO,
    ),
}
