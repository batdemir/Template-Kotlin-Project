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
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ManagerModule {
    @Binds
    abstract fun provideHdmi(myHdmiManagerImp: MyHdmiManagerImp): MyHdmiManager

    @Binds
    abstract fun provideLanguage(myLanguageHandlerImp: MyLanguageManagerImp): MyLanguageManager

    @Binds
    abstract fun provideResource(resourceInitializerImp: MyResourceManagerImp): MyResourceManager

    @Binds
    abstract fun provideStorage(prefStorageManagerImp: MyStorageManagerImp): MyStorageManager

    @Binds
    abstract fun provideTheme(myThemeManagerImp: MyThemeManagerImp): MyThemeManager
}