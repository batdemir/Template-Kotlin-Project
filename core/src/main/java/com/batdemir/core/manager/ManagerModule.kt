package com.batdemir.core.manager

import com.batdemir.core.manager.hdmi.MyHdmiManager
import com.batdemir.core.manager.hdmi.MyHdmiManagerImp
import com.batdemir.core.manager.language.MyLanguageManager
import com.batdemir.core.manager.language.MyLanguageManagerImp
import com.batdemir.core.manager.resource.MyResourceManager
import com.batdemir.core.manager.resource.MyResourceManagerImp
import com.batdemir.core.manager.storage.MyStorageManager
import com.batdemir.core.manager.storage.MyStorageManagerImp
import com.batdemir.core.manager.theme.MyThemeManager
import com.batdemir.core.manager.theme.MyThemeManagerImp
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
