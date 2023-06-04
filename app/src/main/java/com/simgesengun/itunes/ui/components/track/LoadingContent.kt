package com.simgesengun.itunes.ui.components.track

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.simgesengun.itunes.ui.theme.spacing

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.padding(
            vertical = MaterialTheme.spacing.small
        ),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}