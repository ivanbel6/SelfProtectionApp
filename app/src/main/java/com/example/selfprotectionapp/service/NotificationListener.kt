package com.example.selfprotectionapp.service

import android.Manifest
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.selfprotectionapp.R
import com.example.selfprotectionapp.domain.AnalyzeMessageUseCase
import com.example.selfprotectionapp.domain.Threat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotificationListener : NotificationListenerService() {
    @Inject lateinit var analyzeMessageUseCase: AnalyzeMessageUseCase
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val packageName = sbn.packageName
        val source = when {
            packageName.contains("vk") -> "VK"
            packageName.contains("mail") -> "Mail.ru"
            packageName.contains("telegram") -> "Telegram"
            else -> "Unknown"
        }
        val extras = sbn.notification.extras
        val message = extras.getString("android.text") ?: return

        scope.launch @androidx.annotation.RequiresPermission(android.Manifest.permission.POST_NOTIFICATIONS) {
            val threat = analyzeMessageUseCase(message, source)
            if (threat != null) {
                sendNotification(threat)
            }
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun sendNotification(threat: Threat) {
        val builder = NotificationCompat.Builder(this, "threat_channel")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Обнаружена угроза: ${threat.level}")
            .setContentText(threat.recommendation)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        NotificationManagerCompat.from(this).notify(threat.id.toInt(), builder.build())
    }
}