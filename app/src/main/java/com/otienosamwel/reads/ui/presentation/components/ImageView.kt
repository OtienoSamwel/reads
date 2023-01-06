package com.otienosamwel.reads.ui.presentation.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.otienosamwel.reads.ui.theme.ReadsTheme

@Composable
fun ImageView(size: Int, imageLink: String, onClick: () -> Unit) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageLink)
            .crossfade(true)
            .build(),

        contentDescription = null,
        modifier = Modifier
            .width(size.dp)
            .height((size * 1.6).dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable(enabled = true, onClick = onClick),
        contentScale = ContentScale.FillBounds
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
                size = 150,
                imageLink = "",
                onClick = {})
        }
    }
}