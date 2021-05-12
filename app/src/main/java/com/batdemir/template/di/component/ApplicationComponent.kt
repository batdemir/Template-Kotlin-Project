package com.batdemir.template.di.component

import android.content.Context
import com.batdemir.template.di.initializer.AppInitializer
import com.batdemir.template.di.module.InitializerModule
import com.batdemir.template.di.module.ManagerModule
import com.batdemir.template.di.module.MediatorModule
import com.batdemir.template.di.module.RepositoryModule
import com.batdemir.template.di.module.local.LocalModule
import com.batdemir.template.di.module.remote.NetworkModule
import com.batdemir.template.ui.view.MainActivity
import com.batdemir.template.ui.view.SplashActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        InitializerModule::class,
        ManagerModule::class,
        LocalModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        MediatorModule::class
    ]
)
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun initializer(): AppInitializer
    fun inject(splashActivity: SplashActivity)
    fun inject(mainActivity: MainActivity)
    fun homeComponent(): HomeComponent.Factory
    fun stackOverFlowComponent(): StackOverFlowComponent.Factory
    fun githubComponent(): GithubComponent.Factory
    fun settingsComponent(): SettingsComponent.Factory
}