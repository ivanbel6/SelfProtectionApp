package com.example.selfprotectionapp.domain

import kotlinx.coroutines.flow.Flow

interface ThreatRepository {
    suspend fun insertThreat(threat: Threat)
    fun getAllThreats(): Flow<List<Threat>>
}