package com.example.selfprotectionapp.domain

import com.example.selfprotectionapp.data.VkDataSource
import javax.inject.Inject

class FetchVkMessagesUseCase @Inject constructor(
    private val vkDataSource: VkDataSource,
    private val analyzeMessageUseCase: AnalyzeMessageUseCase
) {
    suspend operator fun invoke(): List<Threat> {
        val messages = vkDataSource.fetchMessages()
        val threats = mutableListOf<Threat>()
        messages.forEach { message ->
            analyzeMessageUseCase(message, "VK")?.let { threats.add(it) }
        }
        return threats
    }
}