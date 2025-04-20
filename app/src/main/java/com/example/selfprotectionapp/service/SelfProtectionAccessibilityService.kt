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
    private var textInputCount = 0
    private var lastTextInputTime = 0L

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        when (event.eventType) {
            AccessibilityEvent.TYPE_VIEW_CLICKED -> {
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
            AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED -> {
                val currentTime = System.currentTimeMillis()
                textInputCount++
                if (currentTime - lastTextInputTime < 1000) { // Частый ввод текста
                    scope.launch {
                        threatRepository.insertThreat(
                            Threat(
                                message = "Обнаружен частый ввод текста",
                                level = "Medium",
                                recommendation = "Проверьте, не вводите ли вы данные на подозрительной странице."
                            )
                        )
                    }
                }
                lastTextInputTime = currentTime
            }
        }
    }

    override fun onInterrupt() {}
}