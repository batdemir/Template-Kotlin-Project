package com.batdemir.template.di.module

import com.batdemir.template.di.manager.hdmi.MyHdmiManager
import com.batdemir.template.di.manager.hdmi.MyHdmiManagerImp
import com.batdemir.template.di.manager.language.MyLanguageManager
import com.batdemir.template.di.manager.language.MyLanguageManagerImp
import com.batdemir.template.di.manager.resource.MyResourceManager
import com.batdemir.template.di.manager.resource.MyResourceManagerImp
import com.batdemir.template.di.manager.storage.MyStorageManager
import com.batdemir.template.di.manager.storage.MyStorageManagerImp
import com.batdemir.template.di.manager.theme.MyThemeManager
import com.batdemir.template.di.manager.theme.MyThemeManagerImp
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ManagerModule {
    @Singleton
    @Binds
    abstract fun provideHdmi(myHdmiManagerImp: MyHdmiManagerImp): MyHdmiManager

    @Singleton
    @Binds
    abstract fun provideLanguage(myLanguageHandlerImp: MyLanguageManagerImp): MyLanguageManager

    @Singleton
    @Binds
    abstract fun provideResource(resourceInitializerImp: MyResourceManagerImp): MyResourceManager

    @Singleton
    @Binds
    abstract fun provideStorage(prefStorageManagerImp: MyStorageManagerImp): MyStorageManager

    @Singleton
    @Binds
    abstract fun provideTheme(myThemeManagerImp: MyThemeManagerImp): MyThemeManager
}