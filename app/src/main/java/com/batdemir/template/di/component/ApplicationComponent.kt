package com.batdemir.template.di.component

import android.content.Context
import com.batdemir.template.di.initializer.AppInitializer
import com.batdemir.template.di.module.*
import com.batdemir.template.ui.view.MainActivity
import com.batdemir.template.ui.view.SplashActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        InitializerModule::class,
        LocalModule::class,
        ManagerModule::class,
        NetworkModule::class,
        RepositoryModule::class,
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
    fun dashboardComponent(): DashboardComponent.Factory
    fun notificationsComponent(): NotificationsComponent.Factory
    fun settingsComponent(): SettingsComponent.Factory
}