package com.msayeh.data.di

import com.msayeh.data.repository.TimerRepoImpl
import com.msayeh.domain.repository.TimerRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/02/2024
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    @Singleton
    abstract fun bindTimerRepo(
        timerRepoImpl: TimerRepoImpl,
    ): TimerRepo
}