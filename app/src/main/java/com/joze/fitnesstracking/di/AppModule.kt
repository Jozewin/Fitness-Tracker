package com.joze.fitnesstracking.di

import android.app.Application
import com.joze.fitnesstracking.data.manager.LocalUserManagerImple
import com.joze.fitnesstracking.domain.manager.LocalUserManager
import com.joze.fitnesstracking.domain.usecases.app_entry.AppEntryUseCases
import com.joze.fitnesstracking.domain.usecases.app_entry.ReadAppEntry
import com.joze.fitnesstracking.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) :LocalUserManager = LocalUserManagerImple(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) : AppEntryUseCases{
        return AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )
    }
}