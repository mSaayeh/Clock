package com.msayeh.data.repository

import com.msayeh.data.datasource.local.TimerDao
import com.msayeh.domain.util.InvalidDurationException
import com.msayeh.data.util.toDomainModel
import com.msayeh.data.util.toEntity
import com.msayeh.domain.repository.TimerRepo
import com.msayeh.model.Timer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/02/2024
 */

class TimerRepoImpl @Inject constructor(
    private val dao: TimerDao,
) : TimerRepo {
    override fun getAllTimers(): Flow<List<Timer>> {
        return dao.getAllTimers().map { list -> list.map { it.toDomainModel() } }
    }

    override suspend fun upsertTimer(timer: Timer) {
        if (timer.duration <= 0) {
            throw InvalidDurationException()
        }
        val entity = timer.toEntity()
        dao.insertOrUpdateTimer(entity)
    }

    override suspend fun deleteTimer(timer: Timer) {
        val entity = timer.toEntity()
        dao.deleteTimer(entity)
    }
}