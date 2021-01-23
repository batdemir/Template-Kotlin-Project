package com.batdemir.template.di.component

import android.content.Context
import com.batdemir.template.di.module.LocalModule
import com.batdemir.template.di.module.NetworkModule
import com.batdemir.template.di.module.RepositoryModule
import com.batdemir.template.di.module.StorageModule
import com.batdemir.template.ui.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LocalModule::class, NetworkModule::class, RepositoryModule::class, StorageModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
    fun inject(mainActivity: MainActivity)
    fun homeComponent(): HomeComponent.Factory
    fun dashboardComponent(): DashboardComponent.Factory
    fun notificationsComponent(): NotificationsComponent.Factory
}