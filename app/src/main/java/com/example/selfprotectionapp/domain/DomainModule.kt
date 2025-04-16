package com.example.selfprotectionapp.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideAnalyzeMessageUseCase(repository: ThreatRepository): AnalyzeMessageUseCase {
        return AnalyzeMessageUseCase(repository)
    }
}