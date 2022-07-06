package com.otienosamwel.reads.ui.presentation.components


import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.otienosamwel.reads.ui.theme.ReadsTheme

@Composable
fun ImageView(size: Dp, imageResource: String, onClick: () -> Unit) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(Uri.parse(imageResource).buildUpon().scheme("https").build())
            .crossfade(true)
            .build(),

        contentDescription = null,
        loading = {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentHeight()
            )
        },
        modifier = Modifier
            .width(size)
            .height(size)
            .clip(RoundedCornerShape(3.dp))
            .clickable(enabled = true, onClick = onClick),
    )
}

@Preview
@Composable
fun ImageViewPrev() {
    ReadsTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            ImageView(
                size = 150.dp,
                imageResource = "",
                onClick = {})
        }
    }
}