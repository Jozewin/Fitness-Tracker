package com.joze.fitnesstracking.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.joze.fitnesstracking.data.local.UserDetailsDao
import com.joze.fitnesstracking.data.local.UserDetailsDatabase
import com.joze.fitnesstracking.data.manager.LocalUserManagerImple
import com.joze.fitnesstracking.data.repository.UserDetailsRepository
import com.joze.fitnesstracking.domain.manager.LocalUserManager
import com.joze.fitnesstracking.domain.usecases.app_entry.AppEntryUseCases
import com.joze.fitnesstracking.domain.usecases.app_entry.ReadAppEntry
import com.joze.fitnesstracking.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideUserDetailsDatabase(
        application : Application
    ) : UserDetailsDatabase{
        return Room.databaseBuilder(
            application,
            UserDetailsDatabase::class.java,
            "user_details_database"
        ).build()
    }

    @Provides
    fun provideUserProfileDao(db: UserDetailsDatabase): UserDetailsRepository {
        return UserDetailsRepository(db.userDetailsDao)
    }
}