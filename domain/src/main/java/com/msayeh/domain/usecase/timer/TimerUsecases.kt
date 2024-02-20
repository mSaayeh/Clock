package com.msayeh.domain.usecase.timer

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/02/2024
 */

class TimerUsecases(
    val getAllTimersUsecase: GetAllTimersUsecase,
    val insertTimerUsecase: InsertTimerUsecase,
    val deleteTimerUsecase: DeleteTimerUsecase,
)