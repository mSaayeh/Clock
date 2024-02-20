package com.msayeh.domain.usecase.timer

import com.msayeh.domain.repository.TimerRepo
import com.msayeh.model.Timer

class InsertTimerUsecase(
    private val timerRepo: TimerRepo,
) {
    suspend operator fun invoke(timer: Timer) =
        timerRepo.upsertTimer(timer)
}