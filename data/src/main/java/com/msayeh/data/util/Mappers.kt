package com.msayeh.data.util

import com.msayeh.data.datasource.local.TimerEntity
import com.msayeh.model.Timer

fun TimerEntity.toDomainModel(): Timer =
    Timer(
        id = id,
        name = name,
        duration = duration,
        isRunning = isRunning
    )

fun Timer.toEntity(): TimerEntity = TimerEntity(
    id = id,
    name = name,
    isRunning = isRunning,
    duration = duration
)