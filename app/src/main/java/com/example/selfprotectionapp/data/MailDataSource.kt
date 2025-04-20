package com.example.selfprotectionapp.data

import javax.inject.Inject

class MailDataSource @Inject constructor() {
    suspend fun fetchMessages(): List<String> {
        // Заглушка: IMAP будет позже
        return listOf(
            "Ваш счёт заблокирован, перейдите по http://fake.ru",
            "Приглашение на встречу"
        )
    }
}