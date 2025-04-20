package com.example.selfprotectionapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.selfprotectionapp.domain.AnalyzeMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val analyzeMessageUseCase: AnalyzeMessageUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        // Заглушка для статистики
        updateThreatCount()
    }

    fun analyzeMessages() {
        viewModelScope.launch {
            // Имитация анализа сообщений из VK
            val messages = listOf(
                "Привет, срочно перейди по ссылке http://fake.com",
                "Встреча в 15:00"
            )
            messages.forEach { message ->
                analyzeMessageUseCase(message, "VK")
            }
            _state.value = _state.value.copy(
                status = "Анализ завершён",
                notifications = listOf(
                    Notification("Medium", "Обнаружена подозрительная ссылка"),
                    Notification("High", "Срочный запрос данных — возможный фишинг")
                )
            )
            updateThreatCount()
        }
    }

    private fun updateThreatCount() {
        _state.value = _state.value.copy(threatCount = _state.value.notifications.size)
    }
}

data class HomeState(
    val status: String = "Безопасно",
    val notifications: List<Notification> = emptyList(),
    val threatCount: Int = 0
)