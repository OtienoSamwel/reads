package com.otienosamwel.reads.ui.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.reads.R
import com.otienosamwel.reads.ui.theme.ReadsTheme

@Composable
fun LoginButton(text: String, onClick: () -> Unit, @DrawableRes icon: Int) {
    OutlinedButton(
        onClick = onClick,
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .wrapContentWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(icon),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = Sizes.small)
                .size(24.dp)
        )

        SpaceSmall()

        Text(text = text, Modifier.padding(horizontal = Sizes.small))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginButtonPreview() {
    ReadsTheme {
        LoginButton(
            text = "Continue with Google", onClick = {}, icon = R.drawable.ic_google_logo
        )
    }
}