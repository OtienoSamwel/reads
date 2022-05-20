package com.otienosamwel.reads.ui.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.otienosamwel.reads.R
import com.otienosamwel.reads.data.model.Book
import com.otienosamwel.reads.ui.theme.ReadsTheme

@Composable
fun BookItem(book: Book, onCoverClick: () -> Unit, onAuthorClick: () -> Unit) {
    Column(
        modifier = Modifier.width(100.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        ImageView(
            size = 100.dp,
            imageResource = R.drawable.name_of_the_wind_cover,
            onClick = onCoverClick
        )
        SpaceSmall()
        Text(
            text = book.author,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Black,
            modifier = Modifier.clickable(enabled = true, onClick = onAuthorClick)
        )
        SpaceTiny()

        Text(
            text = book.name,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview
@Composable
fun BookItemPreview() {
    ReadsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                BookItem(
                    book = Book(name = "The name of the wind", author = "Patrick Rothfuss"),
                    onAuthorClick = {},
                    onCoverClick = {})
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun BookItemPreviewRow() {
    ReadsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(20) {
                        BookItem(
                            book = Book(
                                name = "The tombs of Atuan",
                                author = "Patrick Rothfuss"
                            ), onAuthorClick = {}, onCoverClick = {}
                        )
                    }
                }
            }
        }
    }
}