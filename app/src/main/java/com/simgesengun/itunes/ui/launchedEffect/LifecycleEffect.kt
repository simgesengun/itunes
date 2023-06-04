package com.simgesengun.itunes.ui.launchedEffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.launch

@Composable
fun LifecycleEffect(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: (suspend () -> Unit)? = null,
    onPause: (suspend () -> Unit)? = null,
    onStop: (suspend () -> Unit)? = null
) {
    val scope = rememberCoroutineScope()
    val currentOnStart by rememberUpdatedState(onStart)
    val currentOnPause by rememberUpdatedState(onPause)
    val currentOnStop by rememberUpdatedState(onStop)

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            scope.launch {
                when (event) {
                    Lifecycle.Event.ON_START -> currentOnStart?.invoke()
                    Lifecycle.Event.ON_PAUSE -> currentOnPause?.invoke()
                    Lifecycle.Event.ON_STOP -> currentOnStop?.invoke()
                    else -> {}
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}