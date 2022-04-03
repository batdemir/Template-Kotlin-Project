package com.batdemir.core.manager.notification

import android.app.PendingIntent

data class NotificationModel(
    val title: String,
    val message: String,
    val iconRes: Int,
    val onGoing: Boolean = true,
    val contentIntent: PendingIntent? = null,
    val cancelModel: NotificationCancelModel? = null
)
