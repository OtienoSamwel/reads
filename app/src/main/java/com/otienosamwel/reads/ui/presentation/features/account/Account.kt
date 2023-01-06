package com.otienosamwel.reads.ui.presentation.features.account

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.otienosamwel.reads.ui.presentation.components.SpaceAbsolute
import com.otienosamwel.reads.ui.presentation.components.SpaceSmall
import com.otienosamwel.reads.ui.presentation.features.auth.AuthActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Account(accountViewModel: AccountViewModel = hiltViewModel()) {
    val context = LocalContext.current

    Scaffold(
        topBar = { AccountTopAppBar() },
        modifier = Modifier.fillMaxSize()
    ) { scaffoldPaddingValues ->
        Column(
            modifier = Modifier
                .padding(scaffoldPaddingValues)
                .fillMaxSize()
        ) {


            SpaceAbsolute()

            Button(
                onClick = {
                    accountViewModel.clearPreference()
                    context.startActivity(Intent(context, AuthActivity::class.java))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Logout", modifier = Modifier.padding(6.dp))
            }

            SpaceSmall()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountTopAppBar() {
    TopAppBar(title = {
        Text(text = "Account")
    }, modifier = Modifier.fillMaxWidth())
}
