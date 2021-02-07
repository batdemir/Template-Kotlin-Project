package com.batdemir.template.di.manager.language

import com.batdemir.template.R
import com.batdemir.template.app.MyApplication
import com.batdemir.template.di.manager.resource.IResourceManager
import com.batdemir.template.di.manager.storage.IStorageManager
import com.yariksoffice.lingver.Lingver
import java.util.*
import javax.inject.Inject


class LanguageManager @Inject constructor(
    private val resourceManager: IResourceManager,
    private val storageManager: IStorageManager
) : ILanguageManager {
    override fun setDefaultLanguage(application: MyApplication) {
        val languageName =
            storageManager.getString(
                resourceManager.getResources().getString(R.string.KEY_LANGUAGE)
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
                resourceManager.getContext(),
                Locale.forLanguageTag(language.languageCode)
            )
        storageManager.setString(
            resourceManager.getResources().getString(R.string.KEY_LANGUAGE),
            language.languageName
        )
    }
}