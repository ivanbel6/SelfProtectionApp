package com.example.selfprotectionapp.presentation
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TrainingViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(TrainingState())
    val state: StateFlow<TrainingState> = _state

    fun selectOption(index: Int) {
        val correctAnswer = 0 // "Срочно подтвердите данные"
        val isCorrect = index == correctAnswer
        _state.value = _state.value.copy(
            selectedOption = index,
            result = if (isCorrect)
                "Правильно! Это фишинговое сообщение."
            else
                "Неверно. Фишинг часто использует срочные запросы.",
            progress = if (isCorrect) _state.value.progress + 20 else _state.value.progress
        )
    }
}

data class TrainingState(
    val options: List<String> = listOf(
        "Срочно подтвердите данные по ссылке",
        "Встреча в 15:00",
        "Ваш заказ доставлен"
    ),
    val selectedOption: Int? = null,
    val result: String = "",
    val progress: Int = 0
)