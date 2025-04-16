package com.example.selfprotectionapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.selfprotectionapp.domain.Threat
import kotlinx.coroutines.flow.Flow

@Dao
interface ThreatDao {
    @Insert
    suspend fun insert(threat: Threat)

    @Query("SELECT * FROM threats")
    fun getAllThreats(): Flow<List<Threat>>
}