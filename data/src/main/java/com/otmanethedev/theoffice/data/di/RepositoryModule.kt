package com.otmanethedev.theoffice.data.di

import com.otmanethedev.domain.repository.TheOfficeRepository
import com.otmanethedev.theoffice.data.repository.TheOfficeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideTheOfficeRepository(theOfficeRepositoryImpl: TheOfficeRepositoryImpl): TheOfficeRepository
}