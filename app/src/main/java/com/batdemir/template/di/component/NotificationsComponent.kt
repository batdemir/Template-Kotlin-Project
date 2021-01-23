package com.batdemir.template.di.component

import com.batdemir.template.ui.view.notifications.NotificationsFragment
import dagger.Subcomponent

@Subcomponent
interface NotificationsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): NotificationsComponent
    }

    fun inject(notificationsFragment: NotificationsFragment)
}