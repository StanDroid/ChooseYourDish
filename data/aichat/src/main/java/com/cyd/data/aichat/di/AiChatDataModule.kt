package com.cyd.data.aichat.di

import com.cyd.data.aichat.AiChatDataSource
import com.cyd.data.aichat.AiChatRepositoryImpl
import com.cyd.data.aichat.KtorAiChatDataSourceImpl
import com.cyd.domain.aichat.AiChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AiChatDataModule {
    @Binds
    fun bindAiChatDataSource(dataSource: KtorAiChatDataSourceImpl): AiChatDataSource

    @Binds
    fun bindAiChatRepository(repository: AiChatRepositoryImpl): AiChatRepository
}