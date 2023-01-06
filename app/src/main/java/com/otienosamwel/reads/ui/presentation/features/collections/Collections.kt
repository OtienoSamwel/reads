package com.otienosamwel.reads.ui.presentation.features.collections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Collections() {
    Scaffold(
        topBar = { CollectionsTopAppBar() },
        modifier = Modifier.fillMaxSize()
    ) { scaffoldPaddingValues ->
        Column(
            modifier = Modifier
                .padding(scaffoldPaddingValues)
                .fillMaxSize()
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionsTopAppBar() {

    SmallTopAppBar(modifier = Modifier.fillMaxWidth(), title = {
        Text(text = "Collections")
    })
}
