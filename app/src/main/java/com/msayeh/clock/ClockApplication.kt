package com.msayeh.clock

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationChannelGroupCompat
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.HiltAndroidApp

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/02/2024
 */

@HiltAndroidApp
class ClockApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val timersGroup = NotificationChannelGroup(
                Constants.TIMERS_NOTIFICATION_CHANNEL_GROUP_ID,
                "Timers & Counters"
            )
            val timersChannel =
                NotificationChannel(
                    Constants.TIMERS_NOTIFICATION_CHANNEL_ID,
                    "Timers",
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    group = Constants.TIMERS_NOTIFICATION_CHANNEL_GROUP_ID
                }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(timersChannel)
            notificationManager.createNotificationChannelGroup(timersGroup)
        } else {
            val timersGroup =
                NotificationChannelGroupCompat.Builder(Constants.TIMERS_NOTIFICATION_CHANNEL_ID)
                    .build()
            val timersChannel = NotificationChannelCompat.Builder(
                Constants.TIMERS_NOTIFICATION_CHANNEL_ID,
                NotificationManager.IMPORTANCE_LOW
            ).setGroup(Constants.TIMERS_NOTIFICATION_CHANNEL_GROUP_ID)
                .build()
            val notificationManager = getSystemService(NotificationManagerCompat::class.java)
            notificationManager.createNotificationChannel(timersChannel)
            notificationManager.createNotificationChannelGroup(timersGroup)
        }
    }
}