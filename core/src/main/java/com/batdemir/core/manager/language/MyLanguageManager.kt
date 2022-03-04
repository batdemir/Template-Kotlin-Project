package com.batdemir.core.manager.language

import com.batdemir.core.R

interface MyLanguageManager {
    fun setDefaultLanguage()
    fun getCurrentLanguage(): Languages
    fun changeLanguage(language: Languages)
}

enum class Languages(
    val languageName: Int,
    val languageCode: String
) {
    ENGLISH(
        R.string.language_english,
        "en"
    ),
    TURKISH(
        R.string.language_turkish,
        "tr"
    )
}