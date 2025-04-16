package com.example.selfprotectionapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    fun analyzeMessages() {
        viewModelScope.launch {
            // Заглушка: пока имитируем анализ
            _state.value = _state.value.copy(
                status = "Анализ завершён",
                notifications = listOf(
                    Notification("Medium", "Обнаружена подозрительная ссылка"),
                    Notification("High", "Срочный запрос данных — возможный фишинг")
                )
            )
        }
    }
}

data class HomeState(
    val status: String = "Безопасно",
    val notifications: List<Notification> = emptyList()
)