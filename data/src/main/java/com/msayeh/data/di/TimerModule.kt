package com.msayeh.data.di

import com.msayeh.domain.repository.TimerRepo
import com.msayeh.domain.usecase.timer.DeleteTimerUsecase
import com.msayeh.domain.usecase.timer.GetAllTimersUsecase
import com.msayeh.domain.usecase.timer.InsertTimerUsecase
import com.msayeh.domain.usecase.timer.TimerUsecases
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
object TimerModule {
    @Provides
    @Singleton
    fun provideGetAllTimersUsecase(timerRepo: TimerRepo): GetAllTimersUsecase {
        return GetAllTimersUsecase(timerRepo)
    }

    @Provides
    @Singleton
    fun provideInsertTimerUsecase(timerRepo: TimerRepo): InsertTimerUsecase {
        return InsertTimerUsecase(timerRepo)
    }

    @Provides
    @Singleton
    fun provideDeleteTimerUsecase(timerRepo: TimerRepo): DeleteTimerUsecase {
        return DeleteTimerUsecase(timerRepo)
    }

    @Provides
    @Singleton
    fun provideTimerUsecases(
        getAllTimersUsecase: GetAllTimersUsecase,
        insertTimerUsecase: InsertTimerUsecase,
        deleteTimerUsecase: DeleteTimerUsecase,
    ): TimerUsecases {
        return TimerUsecases(
            getAllTimersUsecase = getAllTimersUsecase,
            insertTimerUsecase = insertTimerUsecase,
            deleteTimerUsecase = deleteTimerUsecase
        )
    }
}