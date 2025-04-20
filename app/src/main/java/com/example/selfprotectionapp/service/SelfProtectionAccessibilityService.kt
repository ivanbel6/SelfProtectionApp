package com.example.selfprotectionapp.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import com.example.selfprotectionapp.domain.Threat
import com.example.selfprotectionapp.domain.ThreatRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SelfProtectionAccessibilityService : AccessibilityService() {
    @Inject lateinit var threatRepository: ThreatRepository
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var clickCount = 0
    private var lastClickTime = 0L

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            val currentTime = System.currentTimeMillis()
            clickCount++
            if (currentTime - lastClickTime < 1000) { // Частые клики
                scope.launch {
                    threatRepository.insertThreat(
                        Threat(
                            message = "Обнаружены частые клики",
                            level = "Medium",
                            recommendation = "Замедлите действия, возможна подозрительная активность."
                        )
                    )
                }
            }
            lastClickTime = currentTime
        }
    }

    override fun onInterrupt() {}
}