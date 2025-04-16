package com.example.selfprotectionapp.data

import android.content.Context
import androidx.room.Room
import com.example.selfprotectionapp.domain.ThreatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideThreatDatabase(@ApplicationContext context: Context): ThreatDatabase {
        return Room.databaseBuilder(
            context,
            ThreatDatabase::class.java,
            "threat_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideThreatDao(database: ThreatDatabase): ThreatDao {
        return database.threatDao()
    }

    @Provides
    @Singleton
    fun provideThreatRepository(dao: ThreatDao): ThreatRepository {
        return ThreatRepositoryImpl(dao)
    }
}