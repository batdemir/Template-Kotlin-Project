package com.batdemir.template.di.module

import com.batdemir.template.di.manager.language.ILanguageManager
import com.batdemir.template.di.manager.language.LanguageManager
import com.batdemir.template.di.manager.resource.IResourceManager
import com.batdemir.template.di.manager.resource.ResourceManager
import com.batdemir.template.di.manager.storage.IStorageManager
import com.batdemir.template.di.manager.storage.StorageManager
import com.batdemir.template.di.manager.theme.IThemeManager
import com.batdemir.template.di.manager.theme.ThemeManager
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ManagerModule {
    @Singleton
    @Binds
    abstract fun provideLanguage(languageHandler: LanguageManager): ILanguageManager

    @Singleton
    @Binds
    abstract fun provideResource(resourceInitializer: ResourceManager): IResourceManager

    @Singleton
    @Binds
    abstract fun provideStorage(storageManager: StorageManager): IStorageManager

    @Singleton
    @Binds
    abstract fun provideTheme(themeManager: ThemeManager): IThemeManager
}