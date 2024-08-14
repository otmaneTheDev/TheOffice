package com.otmanethedev.theoffice.data.di

import android.content.Context
import androidx.room.Room
import com.otmanethedev.theoffice.data.local.database.OfficeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTheOfficeDatabase(@ApplicationContext context: Context): OfficeDatabase {
        return Room.databaseBuilder(context, OfficeDatabase::class.java, "theOffice_db").build()
    }

    @Provides
    @Singleton
    fun providePersonDao(officeDatabase: OfficeDatabase) = officeDatabase.personDao()

    @Provides
    @Singleton
    fun provideDeskDao(officeDatabase: OfficeDatabase) = officeDatabase.deskDao()

    @Provides
    @Singleton
    fun provideKeyboardDao(officeDatabase: OfficeDatabase) = officeDatabase.keyboardDao()

    @Provides
    @Singleton
    fun provideScreenDao(officeDatabase: OfficeDatabase) = officeDatabase.screenDao()

    @Provides
    @Singleton
    fun provideOfficeDao(officeDatabase: OfficeDatabase) = officeDatabase.officeDao()
}