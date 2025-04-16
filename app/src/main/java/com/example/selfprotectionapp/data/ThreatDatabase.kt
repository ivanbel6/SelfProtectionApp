package com.example.selfprotectionapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.selfprotectionapp.domain.Threat

@Database(entities = [Threat::class], version = 1, exportSchema = true)
abstract class ThreatDatabase : RoomDatabase() {
    abstract fun threatDao(): ThreatDao
}