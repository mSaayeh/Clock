package com.msayeh.clock.ui.screen.timer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.msayeh.clock.service.TimerService
import com.msayeh.clock.ui.theme.ClockTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * @author Mohannad El-Sayeh email(eng.mohannadelsayeh@gmail.com)
 * @date 12/02/2024
 */

@RootNavGraph(start = true)
@Destination
@Composable
fun TimerListScreen(
    navigator: DestinationsNavigator,
) {
    val context = LocalContext.current
    var remainingTime by remember { mutableStateOf(10000L) }
    val remainingTimeFormatted by derivedStateOf {
        DateUtils.formatElapsedTime(remainingTime / 1000)
    }

    val timeUpdateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == TimerService.TIME_UPDATE_ACTION) {
                remainingTime = intent.getLongExtra("remainingTime", 0L)
            }
        }
    }

    DisposableEffect(key1 = Unit) {
        val intentFilter = IntentFilter(TimerService.TIME_UPDATE_ACTION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.registerReceiver(
                timeUpdateReceiver,
                intentFilter,
                Context.RECEIVER_EXPORTED
            )
        } else {
            context.registerReceiver(timeUpdateReceiver, intentFilter)
        }

        onDispose {
            context.unregisterReceiver(timeUpdateReceiver)
        }
    }
    TimerListContent(remainingTimeFormatted)
}

@Composable
fun TimerListContent(remainingTime: String) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                val intent = Intent(context, TimerService::class.java)
                intent.setFlags(TimerService.FLAGS.START.ordinal)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(intent)
                } else {
                    context.startService(intent)
                }
            }) {
                Text(text = "Start")
            }
            Text(text = remainingTime)
        }

    }
}

@Preview
@Composable
fun TimerListScreenPreview() {
    ClockTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            TimerListContent("10:00")
        }
    }
}