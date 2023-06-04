package com.simgesengun.itunes.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.simgesengun.itunes.R
import com.simgesengun.itunes.ui.theme.ITunesTheme

@Composable
fun DeleteButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedIconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_close_24),
            contentDescription = "Delete"
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ITunesTheme {
        DeleteButton(onClick = {})
    }
}