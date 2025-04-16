package com.example.selfprotectionapp.domain

import java.util.regex.Pattern
import javax.inject.Inject

class AnalyzeMessageUseCase @Inject constructor(
    private val threatRepository: ThreatRepository
) {
    suspend operator fun invoke(message: String, source: String): Threat? {
        val suspiciousPatterns = listOf(
            Pattern.compile("http[s]?://[^\\s]+"),
            Pattern.compile("(?i)срочно|подтвердите|переведите")
        )
        val threats = mutableListOf<String>()
        suspiciousPatterns.forEach { pattern ->
            if (pattern.matcher(message).find()) {
                threats.add(pattern.pattern())
            }
        }

        return when (threats.size) {
            0 -> null
            1 -> {
                val threat = Threat(
                    message = message,
                    level = "Medium",
                    recommendation = "Будьте осторожны, проверьте источник сообщения ($source)."
                )
                threatRepository.insertThreat(threat)
                threat
            }
            else -> {
                val threat = Threat(
                    message = message,
                    level = "High",
                    recommendation = "Не взаимодействуйте с сообщением из $source, возможна атака."
                )
                threatRepository.insertThreat(threat)
                threat
            }
        }
    }
}