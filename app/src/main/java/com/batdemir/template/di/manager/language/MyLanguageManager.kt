package com.batdemir.template.di.manager.language

import com.batdemir.template.R
import com.batdemir.template.app.MyApplication

interface MyLanguageManager {
    fun setDefaultLanguage(application: MyApplication)
    fun getCurrentLanguage(): Languages
    fun changeLanguage(
        language: Languages,
        application: MyApplication? = null
    )
}

enum class Languages(
    val languageName: Int,
    val languageCode: String
) {
    ENGLISH(
        R.string.language_english,
        "en-US"
    ),
    TURKISH(
        R.string.language_turkish,
        "tr-TR"
    )
}