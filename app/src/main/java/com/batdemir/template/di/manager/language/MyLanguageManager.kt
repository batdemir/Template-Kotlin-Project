package com.batdemir.template.di.manager.language

import com.batdemir.template.app.MyApplication

interface MyLanguageManager {
    fun setDefaultLanguage(application: MyApplication)
    fun changeLanguage(language: Languages)
}

enum class Languages(val languageName: String, val languageCode: String) {
    ENGLISH("English", "en-US"),
    TURKISH("Türkçe", "tr-TR")
}