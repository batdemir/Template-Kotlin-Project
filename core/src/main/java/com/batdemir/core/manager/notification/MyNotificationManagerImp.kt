package com.batdemir.core.manager.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.batdemir.core.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MyNotificationManagerImp @Inject constructor(
    @ApplicationContext private val context: Context,
) : MyNotificationManager {
    override fun createNotification(notificationModel: NotificationModel): Notification {
        createChannel()
        val notificationBuilder = NotificationCompat
            .Builder(context, NotificationConstants.CHANNEL_ID)
            .setContentTitle(notificationModel.title)
            .setTicker(notificationModel.title)
            .setContentText(notificationModel.message)
            .setSmallIcon(notificationModel.iconRes)
            .setOngoing(notificationModel.onGoing)
            .setStyle(NotificationCompat.BigTextStyle().bigText(notificationModel.message))
            .setPriority(NotificationManager.IMPORTANCE_LOW)
        notificationModel.contentIntent?.let {
            notificationBuilder.setContentIntent(it)
        }
        notificationModel.cancelModel?.let {
            notificationBuilder.addAction(it.cancelIconRes, it.cancel, it.cancelIntent)
        }
        return notificationBuilder.build()
    }

    private fun createChannel() {
        val serviceChannel = NotificationChannel(
            NotificationConstants.CHANNEL_ID,
            context.getString(R.string.notification_name),
            NotificationManager.IMPORTANCE_LOW
        )
        val notificationManager: NotificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(serviceChannel)
    }
}
