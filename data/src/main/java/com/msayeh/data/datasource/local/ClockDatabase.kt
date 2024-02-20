package com.msayeh.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/02/2024
 */

@Database(entities = [TimerEntity::class], version = 1)
abstract class ClockDatabase: RoomDatabase() {
    abstract val timerDao: TimerDao
}