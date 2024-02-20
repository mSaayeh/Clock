package com.msayeh.clock.service

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.text.format.DateUtils
import android.util.Log
import androidx.core.app.NotificationCompat
import com.msayeh.clock.Constants
import com.msayeh.clock.R

class TimerService : Service() {

    private var remainingTime = 10000L
    private val timers = HashMap<Int, CountDownTimer>()
    private var id: Int = 1
    private val LOG_TAG = "Timer Service"

    private val notification
        get() = NotificationCompat.Builder(this, Constants.TIMERS_NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Timer")
            .setContentText(DateUtils.formatElapsedTime(remainingTime / 1000L))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            /*.addAction(
                R.drawable.ic_launcher_foreground,
                "Cancel",
                PendingIntent.getService(
                    this,
                    1,
                    Intent(this, TimerService::class.java)
                        .setFlags(FLAGS.RESET.ordinal)
                        .putExtra("id", id)
                    ,
                    PendingIntent.FLAG_MUTABLE
                )
            )*/
            .build()


    override fun onCreate() {
        super.onCreate()
        startForeground(id, notification)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    // I can use a generated ID and use it as the notification ID and map key instead
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        var id = intent?.getIntExtra("hash", -1)
        Log.e(LOG_TAG, "Received Hash: $id")
        val countDownTimer = if (timers[id] == null) {
            val newTimer = object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    remainingTime = millisUntilFinished
                    if (shouldUpdateNotification()) {
                        updateNotification(id)
                    }
                    Log.e(LOG_TAG, "Sent Hash: $id")
                    sendUpdateBroadcast()
                }

                override fun onFinish() {
                    stopSelf()
                }
            }
//            TODO: startForeground() to stop the application from crashing
            timers[id] = newTimer
            newTimer
        } else {
            timers[id]
        }
        if (intent?.flags == FLAGS.START.ordinal) {
            countDownTimer?.start()
        } else if (intent?.flags == FLAGS.RESET.ordinal) {
            countDownTimer?.onFinish()
            Log.e(LOG_TAG, "Finished")
//            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
    }

    private fun shouldUpdateNotification(): Boolean {
//        getSystemService(NotificationManager::class.java).getNotificationChannel(Constants.TIMERS_NOTIFICATION_CHANNEL_ID).describeContents()
        return true
    }

    private fun sendUpdateBroadcast() {
        Intent(TIME_UPDATE_ACTION).apply {
            putExtra("remainingTime", remainingTime)
            sendBroadcast(this)
        }
    }

    private fun updateNotification(id: Int) {
        // TODO: Don't create a new notification if the old one is dismissed
        getSystemService(NotificationManager::class.java).apply {
            notify(id, notification)
        }
    }

    private fun cancelNotification(id: Int) {
        getSystemService(NotificationManager::class.java).apply {
            cancel(id)
        }
    }

    enum class FLAGS {
        START, PAUSE, RESET
    }

    companion object {
        const val TIME_UPDATE_ACTION = "TIME_UPDATE"
    }
}