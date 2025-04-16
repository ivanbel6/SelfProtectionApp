package com.example.selfprotectionapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "threats")
data class Threat(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val message: String,
    val level: String, // "Low", "Medium", "High"
    val recommendation: String,
    val timestamp: Long = System.currentTimeMillis()
)