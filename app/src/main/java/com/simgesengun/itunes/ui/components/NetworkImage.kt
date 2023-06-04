package com.simgesengun.itunes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.simgesengun.itunes.R
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NetworkImage(
    imageUrl: String,
    contentDesc: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    colorFilter: ColorFilter? = null
) {
    GlideImage(
        imageModel = { imageUrl },
        modifier = modifier,
        imageOptions = ImageOptions(
            contentDescription = contentDesc,
            contentScale = contentScale,
            colorFilter = colorFilter
        ),
        previewPlaceholder = R.drawable.outline_image_24,
        loading = {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(
                        shimmerBrush(
                            targetValue = 800f,
                            showShimmer = true
                        )
                    )
            )
        },
        failure = {
            Image(
                painter = painterResource(
                    id = R.drawable.outline_image_not_supported_24
                ),
                contentDescription = "Error",
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}