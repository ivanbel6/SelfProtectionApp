package com.example.selfprotectionapp.domain

import com.example.selfprotectionapp.data.TelegramDataSource
import javax.inject.Inject

class FetchTelegramMessagesUseCase @Inject constructor(
    private val telegramDataSource: TelegramDataSource,
    private val analyzeMessageUseCase: AnalyzeMessageUseCase
) {
    suspend operator fun invoke(): List<Threat> {
        val messages = telegramDataSource.fetchMessages()
        val threats = mutableListOf<Threat>()
        messages.forEach { message ->
            analyzeMessageUseCase(message, "Telegram")?.let { threats.add(it) }
        }
        return threats
    }
}