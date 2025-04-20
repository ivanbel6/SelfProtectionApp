package com.example.selfprotectionapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.selfprotectionapp.domain.Threat

@Dao
interface ThreatDao {
    @Insert
    suspend fun insertThreat(threat: Threat)

    @Query("SELECT * FROM threats")
    suspend fun getAllThreats(): List<Threat>
}