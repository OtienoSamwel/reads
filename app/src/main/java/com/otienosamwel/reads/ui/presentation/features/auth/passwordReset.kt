package com.otienosamwel.reads.ui.presentation.features.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dsc.form_builder.TextFieldState
import com.otienosamwel.reads.ui.presentation.components.SpaceLarge
import com.otienosamwel.reads.ui.presentation.components.SpaceMedium
import com.otienosamwel.reads.ui.presentation.components.SpaceSmall
import io.ktor.http.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordRest(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {
    val emailState =
        authViewModel.passwordResetState.getState<TextFieldState>(AuthStateConstants.EMAIL_STATE)

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = "Password Reset") })
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SpaceMedium()

            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.change(it) },
                label = { Text("Email") },
                modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth()
            )

            SpaceSmall()

            Button(
                onClick = {
                    authViewModel.resetPassword()
                    navController.navigate(AuthScreens.Login.route)
                },
                modifier = Modifier.fillMaxWidth(.7f)
            ) {
                Text("Reset")
            }
        }
    }
}