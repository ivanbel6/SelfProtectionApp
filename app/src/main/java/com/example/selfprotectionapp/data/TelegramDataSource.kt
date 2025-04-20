package com.example.selfprotectionapp.data

import javax.inject.Inject

class TelegramDataSource @Inject constructor() {
    suspend fun fetchMessages(): List<String> {
        // Заглушка: TDLib будет позже
        return listOf(
            "Срочно подтвердите данные по ссылке http://fake.org",
            "Привет, как дела?"
        )
    }
}