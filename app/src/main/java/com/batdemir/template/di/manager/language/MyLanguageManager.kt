package com.batdemir.template.di.manager.language

import com.batdemir.template.R

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