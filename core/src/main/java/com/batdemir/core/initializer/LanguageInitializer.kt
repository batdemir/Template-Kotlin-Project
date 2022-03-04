package com.batdemir.core.initializer

import android.content.Context
import androidx.startup.Initializer
import com.batdemir.core.manager.language.MyLanguageManager
import javax.inject.Inject

class LanguageInitializer : Initializer<MyLanguageManager> {
    @Inject
    lateinit var myLanguageManager: MyLanguageManager

    override fun create(context: Context): MyLanguageManager {
        InitializerEntryPoint.resolve(context).inject(this)
        myLanguageManager.setDefaultLanguage()
        return myLanguageManager
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}