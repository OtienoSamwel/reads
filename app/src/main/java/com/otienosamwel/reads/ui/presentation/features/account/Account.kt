package com.otienosamwel.reads.ui.presentation.features.account

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
fun Account() {
    Scaffold(
        topBar = { AccountTopAppBar() },
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

@Composable
fun AccountTopAppBar() {
    SmallTopAppBar(modifier = Modifier.fillMaxWidth(), title = {
        Text(text = "Account")
    })
}

