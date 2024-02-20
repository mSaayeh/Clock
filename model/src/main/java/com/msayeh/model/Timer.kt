package com.msayeh.model

data class Timer(
    val name: String,
    val duration: Long,
    val isRunning: Boolean,
    val id: Int = 0,
)