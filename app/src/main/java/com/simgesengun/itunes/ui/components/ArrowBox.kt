package com.simgesengun.itunes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.simgesengun.itunes.R
import com.simgesengun.itunes.ui.theme.ITunesTheme
import com.simgesengun.itunes.ui.theme.spacing

@Composable
fun ArrowBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
            .aspectRatio(1f)
            .padding(MaterialTheme.spacing.extraSmall)
            .height(IntrinsicSize.Min),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = "Arrow",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ITunesTheme {
        ArrowBox()
    }
}