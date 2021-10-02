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
        changeLanguage(
            getCurrentLanguage(),
            application
        )
    }

    override fun getCurrentLanguage(): Languages {
        val languageName = myStorageManager.getString(
            myResourceManager.getResources()
                .getString(R.string.KEY_LANGUAGE)
        )
        return if (languageName.isEmpty()) {
            Languages.values()
                .first { x ->
                    x.languageCode == Locale.getDefault()
                        .toLanguageTag()
                }
        } else {
            Languages.values()
                .first { x ->
                    myResourceManager.getResources()
                        .getString(x.languageName) == languageName
                }
        }
    }

    override fun changeLanguage(
        language: Languages,
        application: MyApplication?
    ) {
        try {
            Lingver.getInstance()
                .setLocale(
                    myResourceManager.getContext(),
                    Locale.forLanguageTag(language.languageCode)
                )
        } catch (e: IllegalStateException) {
            Lingver.init(
                application!!,
                language.languageCode
            )
        }
        myStorageManager.setString(
            myResourceManager.getResources()
                .getString(R.string.KEY_LANGUAGE),
            myResourceManager.getResources()
                .getString(language.languageName)
        )
    }
}