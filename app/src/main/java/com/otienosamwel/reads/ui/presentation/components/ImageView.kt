package com.otienosamwel.reads.ui.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.otienosamwel.reads.R
import com.otienosamwel.reads.ui.theme.ReadsTheme

@Composable
fun ImageView(size: Dp, @DrawableRes imageResource: Int, onClick: () -> Unit) {
    SubcomposeAsyncImage(
        model = imageResource,
        contentDescription = null,
        loading = { CircularProgressIndicator() },
        modifier = Modifier
            .width(size)
            .wrapContentHeight()
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
                imageResource = R.drawable.name_of_the_wind_cover,
                onClick = {})
        }
    }
}