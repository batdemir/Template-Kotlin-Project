package com.batdemir.template.di.module

import com.batdemir.template.di.initializer.Initializer
import com.batdemir.template.di.initializer.LanguageInitializer
import com.batdemir.template.di.initializer.ThemeInitializer
import com.batdemir.template.di.initializer.TimberInitializer
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class InitializerModule {
    @Binds
    @IntoSet
    abstract fun timberInitialize(timberInitializer: TimberInitializer): Initializer

    @Binds
    @IntoSet
    abstract fun languageInitialize(languageInitializer: LanguageInitializer): Initializer

    @Binds
    @IntoSet
    abstract fun themeInitialize(themeInitializer: ThemeInitializer): Initializer
}