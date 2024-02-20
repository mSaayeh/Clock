package com.msayeh.domain.usecase.timer

import com.msayeh.domain.repository.TimerRepo
import com.msayeh.model.Timer

class GetAllTimersUsecase(
    private val timerRepo: TimerRepo,
) {
    operator fun invoke(timer: Timer) =
        timerRepo.getAllTimers()
}