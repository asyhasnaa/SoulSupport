package com.dicoding.soulsupport.ui.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import com.dicoding.soulsupport.utils.ID_REPEATING
import androidx.core.app.NotificationCompat
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.ui.chat.ChatActivity
import com.dicoding.soulsupport.utils.NOTIFICATION_CHANNEL_ID
import com.dicoding.soulsupport.utils.NOTIFICATION_CHANNEL_NAME
import com.dicoding.soulsupport.utils.NOTIFICATION_ID
import java.util.*

class DailyReminder : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        showNotification(context)
    }

    fun setDailyReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyReminder::class.java)
        val calendar = Calendar.getInstance()
        calendar.apply {
            set(Calendar.HOUR_OF_DAY,12)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, PendingIntent.FLAG_MUTABLE)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        Toast.makeText(context, "One time alarm set up", Toast.LENGTH_SHORT).show()
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyReminder::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, PendingIntent.FLAG_IMMUTABLE)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)
    }

    private fun showNotification(context: Context) {
        val notificationStyle = NotificationCompat.InboxStyle()
        val channelId = NOTIFICATION_CHANNEL_ID
        val channelName = NOTIFICATION_CHANNEL_NAME
        val notificationId = NOTIFICATION_ID
        val repeatingId = ID_REPEATING


        val intent = Intent(context, ChatActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(context)
            .addParentStack(ChatActivity::class.java)
            .addNextIntent(intent)
            .getPendingIntent(repeatingId,PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notifSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notificasi)
            .setContentTitle("Hai Apa Kabar")
            .setContentText("Semoga Hari hari kamu baik,Sarah akan menemani mu kapan pun")
            .setStyle(notificationStyle)
            .setSound(notifSound)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            builder.setChannelId(channelId)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManager.notify(notificationId, notification)
    }
}