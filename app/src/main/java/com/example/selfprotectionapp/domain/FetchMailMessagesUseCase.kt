package com.example.selfprotectionapp.domain

import com.example.selfprotectionapp.data.MailDataSource
import javax.inject.Inject

class FetchMailMessagesUseCase @Inject constructor(
    private val mailDataSource: MailDataSource,
    private val analyzeMessageUseCase: AnalyzeMessageUseCase
) {
    suspend operator fun invoke(): List<Threat> {
        val messages = mailDataSource.fetchMessages()
        val threats = mutableListOf<Threat>()
        messages.forEach { message ->
            analyzeMessageUseCase(message, "Mail.ru")?.let { threats.add(it) }
        }
        return threats
    }
}