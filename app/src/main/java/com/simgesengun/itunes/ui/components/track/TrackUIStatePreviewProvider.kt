package com.simgesengun.itunes.ui.components.track

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.simgesengun.itunes.ui.uiState.TrackUIState

class TrackUIStatePreviewProvider : PreviewParameterProvider<TrackUIState> {
    override val values = sequenceOf(
        TrackUIState(
            trackId=215632626,
            artistName="George Stevens",
            collectionName="Shane / Rio Grande Double Feature",
            trackName="Shane",
            previewUrl="https://video-ssl.itunes.apple.com/itunes-assets/Video128/v4/10/1c/7c/101c7cb0-0983-34fb-6b06-fbebf4655187/mzvf_7060371424413340634.640x360.h264lc.U.p.m4v",
            artworkUrl100="https://is3-ssl.mzstatic.com/image/thumb/Video118/v4/bd/87/9c/bd879cfe-19e4-1188-83b5-c67aee9fe77e/pr_source.jpg/100x100bb.jpg",
            trackPrice="12.99 USD",
            releaseDate="1953-04-23T08:00:00Z"
        )
    )
}