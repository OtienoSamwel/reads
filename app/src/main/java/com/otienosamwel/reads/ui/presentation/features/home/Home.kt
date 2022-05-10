package com.otienosamwel.reads.ui.presentation.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.otienosamwel.reads.ui.presentation.components.Sizes

@Composable
fun Home(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(Sizes.small)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Home", style = MaterialTheme.typography.headlineMedium)
    }
}