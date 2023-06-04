package com.simgesengun.itunes.ui.components.progressDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.simgesengun.itunes.ui.theme.spacing

@Composable
fun ITunesProgressDialog(
    isOpen: Boolean
) {
    if (isOpen) {
        Content()
    }
}

@Composable
private fun Content() {
    Dialog(
        content = {
            Box(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = CircleShape
                ).padding(MaterialTheme.spacing.medium),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        onDismissRequest = { }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Content()
}