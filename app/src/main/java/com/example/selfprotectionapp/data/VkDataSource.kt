package com.example.selfprotectionapp.data

import com.example.selfprotectionapp.domain.Threat
import javax.inject.Inject

class VkDataSource @Inject constructor() {
    suspend fun fetchMessages(): List<String> {
        // Заглушка: реальный VK API будет позже
        return listOf(
            "Привет, срочно перейди по ссылке http://fake.com",
            "Как дела?"
        )
    }
}