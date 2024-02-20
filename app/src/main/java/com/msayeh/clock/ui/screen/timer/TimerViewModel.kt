package com.msayeh.clock.ui.screen.timer

import androidx.lifecycle.ViewModel
import com.msayeh.domain.usecase.timer.TimerUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/02/2024
 */
@HiltViewModel
class TimerViewModel @Inject constructor(
    private val timerUsecases: TimerUsecases,
) : ViewModel() {

}