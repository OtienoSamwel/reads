package com.otienosamwel.reads.ui.presentation.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.otienosamwel.reads.ui.presentation.components.Sizes
import com.otienosamwel.reads.ui.theme.ReadsTheme

@Composable
fun Home(navController: NavController?) {
    Column(
        modifier = Modifier
            .padding(Sizes.small)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Home Alone", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomePreview() {
    ReadsTheme {
        Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            Home(navController = null)
        }
    }
}