package com.otienosamwel.reads.ui.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.otienosamwel.reads.data.model.Item

@Composable
fun BookItem(
    book: Item,
    coverLink: String,
    onCoverClick: () -> Unit,
    onAuthorClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(90.dp),
        horizontalAlignment = Alignment.Start,
    ) {

        ImageView(
            size = 90,
            imageLink = coverLink,
            onClick = onCoverClick
        )

        SpaceSmall()

        Text(
            text = book.volumeInfo?.authors?.take(2)?.joinToString(", ") ?: "Author unavailable",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .clickable(enabled = true, onClick = onAuthorClick),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        SpaceTiny()

        Text(
            text = book.volumeInfo?.title ?: "Title unavailable",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}