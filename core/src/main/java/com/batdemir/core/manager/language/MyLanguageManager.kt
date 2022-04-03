package com.batdemir.core.manager.language

interface MyLanguageManager {
    fun setDefaultLanguage()
    fun getCurrentLanguage(): Languages
    fun changeLanguage(language: Languages)
}
