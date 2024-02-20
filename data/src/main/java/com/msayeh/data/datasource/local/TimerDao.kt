package com.msayeh.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/02/2024
 */

@Dao
interface TimerDao {
    // Insert or update a timer
    @Upsert
    suspend fun insertOrUpdateTimer(timerEntity: TimerEntity)

    // Get all timers
    @Query("SELECT * FROM timerentity")
    fun getAllTimers(): Flow<List<TimerEntity>>

    // Delete a timer
    @Delete
    suspend fun deleteTimer(timerEntity: TimerEntity)
}