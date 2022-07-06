package com.otienosamwel.reads.ui.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.otienosamwel.reads.data.model.Item

@Composable
fun BookItem(book: Item, coverLink: String, onCoverClick: () -> Unit, onAuthorClick: () -> Unit) {
    Column(
        modifier = Modifier.width(100.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        ImageView(
            size = 100.dp,
            imageResource = coverLink,
            onClick = onCoverClick
        )
        SpaceSmall()
        Text(
            text = book.volumeInfo?.authors?.first() ?: "Author unavailable",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Black,
            modifier = Modifier.clickable(enabled = true, onClick = onAuthorClick)
        )
        SpaceTiny()

        Text(
            text = book.volumeInfo?.title ?: "Title unavailable",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold
        )
    }
}