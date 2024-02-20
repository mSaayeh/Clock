package com.msayeh.domain.repository

import com.msayeh.model.Timer
import kotlinx.coroutines.flow.Flow

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/02/2024
 */
interface TimerRepo {
    fun getAllTimers(): Flow<List<Timer>>

    suspend fun upsertTimer(timer: Timer)

    suspend fun deleteTimer(timer: Timer)
}