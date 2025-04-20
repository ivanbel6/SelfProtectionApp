package com.example.selfprotectionapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject



class TelegramDataSource @Inject constructor() {
    suspend fun fetchMessages(): List<String> = withContext(Dispatchers.IO) {
        // Заглушка для TDLib
        // Реальная интеграция требует настройки TDLib и авторизации
        listOf(
            "Срочно подтвердите данные по ссылке http://fake.org",
            "Привет, как дела?"
        )
    }
}