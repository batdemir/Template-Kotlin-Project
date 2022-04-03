package com.batdemir.core.manager.notification

import android.app.Notification

interface MyNotificationManager {
    fun createNotification(notificationModel: NotificationModel): Notification
}
