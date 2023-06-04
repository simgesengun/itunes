package com.simgesengun.itunes.ui.components.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.simgesengun.itunes.R
import com.simgesengun.itunes.ui.theme.ITunesTheme
import com.simgesengun.itunes.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    toolbarUIState: ToolbarUIState?,
    onBackButtonClickListener: () -> Unit
) {
    toolbarUIState?.let { uiState ->
        TopAppBar(
            modifier = Modifier,
            title = {
                Row(
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                ) {
                    toolbarUIState.imageResId?.let {
                        Image(
                            painterResource(id = it),
                            contentDescription = "Logo",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    Text(
                        text = stringResource(id = uiState.title)
                    )
                }
            },
            navigationIcon = {
                if (uiState.isBackButtonEnabled) {
                    IconButton(onClick = onBackButtonClickListener) {
                        Icon(
                            painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back"
                        )
                    }
                }
            }
        )
    }
}

@Composable
@Preview
private fun Preview(
    @PreviewParameter(ToolbarPreviewProvider::class) uiState: ToolbarUIState
) {
    ITunesTheme {
        Toolbar(
            toolbarUIState = uiState,
            onBackButtonClickListener = { }
        )
    }
}

class ToolbarPreviewProvider : PreviewParameterProvider<ToolbarUIState> {
    override val values = sequenceOf(ToolbarUIState.Main, ToolbarUIState.Detail)
}