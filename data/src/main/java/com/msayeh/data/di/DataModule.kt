package com.msayeh.data.di

import android.app.Application
import androidx.room.Room
import com.msayeh.data.datasource.local.ClockDatabase
import com.msayeh.data.datasource.local.TimerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/02/2024
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideClockDatabase(application: Application): ClockDatabase {
        return Room.databaseBuilder(
            application,
            ClockDatabase::class.java,
            "clock_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTimerDao(clockDatabase: ClockDatabase): TimerDao {
        return clockDatabase.timerDao
    }
}