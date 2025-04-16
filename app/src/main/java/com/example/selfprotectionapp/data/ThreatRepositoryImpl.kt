package com.example.selfprotectionapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.selfprotectionapp.domain.Threat
import com.example.selfprotectionapp.domain.ThreatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ThreatRepositoryImpl(private val dao: ThreatDao) : ThreatRepository {
    override suspend fun insertThreat(threat: Threat) {
        dao.insert(threat)
    }

    override fun getAllThreats(): Flow<List<Threat>> {
        return dao.getAllThreats()
    }
}

@Entity(tableName = "threats")
data class ThreatEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val message: String,
    val level: String,
    val recommendation: String,
    val timestamp: Long
)

fun ThreatEntity.toDomain() = Threat(
    id = id,
    message = message,
    level = level,
    recommendation = recommendation,
    timestamp = timestamp
)

fun Threat.toEntity() = ThreatEntity(
    id = id,
    message = message,
    level = level,
    recommendation = recommendation,
    timestamp = timestamp
)