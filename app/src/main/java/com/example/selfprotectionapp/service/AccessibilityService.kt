package com.example.selfprotectionapp.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import com.example.selfprotectionapp.domain.ThreatRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccessibilityService : AccessibilityService() {
    @Inject lateinit var threatRepository: ThreatRepository

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        // Заглушка: логика аналитики (например, частота кликов)
        if (event.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            // Логирование действий
        }
    }

    override fun onInterrupt() {}
}