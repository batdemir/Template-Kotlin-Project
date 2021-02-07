package com.batdemir.template.di.initializer

import com.batdemir.template.app.MyApplication
import com.batdemir.template.di.manager.language.ILanguageManager
import javax.inject.Inject

class LanguageInitializer @Inject constructor(
    private val languageManager: ILanguageManager
) : Initializer {
    override fun initialize(application: MyApplication) {
        languageManager.setDefaultLanguage(application)
    }
}