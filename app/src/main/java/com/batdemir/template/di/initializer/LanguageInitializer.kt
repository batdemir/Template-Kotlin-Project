package com.batdemir.template.di.initializer

import com.batdemir.template.app.MyApplication
import com.batdemir.template.di.manager.language.MyLanguageManager
import javax.inject.Inject

class LanguageInitializer @Inject constructor(
    private val myLanguageManager: MyLanguageManager
) : Initializer {
    override fun initialize(application: MyApplication) {
        myLanguageManager.setDefaultLanguage(application)
    }
}