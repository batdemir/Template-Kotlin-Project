package com.batdemir.template.di.manager.language

import com.batdemir.template.R
import com.batdemir.template.app.MyApplication
import com.batdemir.template.di.manager.resource.MyResourceManager
import com.batdemir.template.di.manager.storage.MyStorageManager
import com.yariksoffice.lingver.Lingver
import java.util.*
import javax.inject.Inject


class MyLanguageManagerImp @Inject constructor(
    private val myResourceManager: MyResourceManager,
    private val myStorageManager: MyStorageManager
) : MyLanguageManager {
    override fun setDefaultLanguage(application: MyApplication) {
        val languageName = myStorageManager.getString(
            myResourceManager.getResources().getString(R.string.KEY_LANGUAGE)
        )
        if (languageName.isEmpty())
            Lingver.init(
                application,
                Locale.forLanguageTag(Languages.ENGLISH.languageCode)
            )
        else
            Lingver.init(
                application,
                Locale.forLanguageTag(
                    Languages.values().first { x -> x.languageName == languageName }.languageCode
                )
            )
    }

    override fun changeLanguage(language: Languages) {
        Lingver.getInstance()
            .setLocale(
                myResourceManager.getContext(),
                Locale.forLanguageTag(language.languageCode)
            )
        myStorageManager.setString(
            myResourceManager.getResources().getString(R.string.KEY_LANGUAGE),
            language.languageName
        )
    }
}