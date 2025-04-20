package com.example.selfprotectionapp.domain

import com.example.selfprotectionapp.data.MailDataSource
import com.example.selfprotectionapp.data.VkDataSource
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

    @Provides
    fun provideFetchVkMessagesUseCase(
        vkDataSource: VkDataSource,
        analyzeMessageUseCase: AnalyzeMessageUseCase
    ): FetchVkMessagesUseCase {
        return FetchVkMessagesUseCase(vkDataSource, analyzeMessageUseCase)
    }

    @Provides
    fun provideFetchMailMessagesUseCase(
        mailDataSource: MailDataSource,
        analyzeMessageUseCase: AnalyzeMessageUseCase
    ): FetchMailMessagesUseCase {
        return FetchMailMessagesUseCase(mailDataSource, analyzeMessageUseCase)
    }
}