package com.otienosamwel.reads.ui.presentation.features.auth.signIn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reads.R
import com.otienosamwel.reads.ui.presentation.components.LoginButton
import com.otienosamwel.reads.ui.presentation.components.SpaceLarge
import com.otienosamwel.reads.ui.presentation.components.SpaceMedium
import com.otienosamwel.reads.ui.presentation.components.SpaceSmall
import com.otienosamwel.reads.ui.presentation.features.auth.AuthScreens
import com.otienosamwel.reads.ui.presentation.features.auth.AuthViewModel
import com.otienosamwel.reads.ui.theme.ReadsTheme
import com.otienosamwel.reads.utils.toast

@Composable
fun Login(
    authViewModel: AuthViewModel,
    navController: NavController,
    signInWithGoogle: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .scrollable(state = scrollState, orientation = Orientation.Vertical),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeText()

        SpaceLarge()
        SpaceMedium()

        Text(
            text = "Login",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium
        )

        SpaceMedium()

        EmailField(state = authViewModel.loginState)
        if (authViewModel.loginState.emailHasError) ErrorMessage(message = "email not valid")

        SpaceSmall()

        PasswordField(state = authViewModel.loginState)
        if (authViewModel.loginState.passwordHasError) ErrorMessage(message = "password cannot be empty")

        SpaceMedium()

        SingInButton(viewModel = authViewModel, state = authViewModel.loginState)

        SpaceSmall()

        DoNotHaveAnAccount(navController = navController)

        SpaceMedium()

        Or()

        SpaceMedium()

       ContinueWithGoogle(onClick = signInWithGoogle)
    }
}


@Composable
fun SingInButton(viewModel: AuthViewModel, state: LoginState) {
    val context = LocalContext.current

    OutlinedButton(
        onClick = { if (state.validate()) context.toast("Email and passcode validated") },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Sign In")
    }
}

@Composable
fun EmailField(state: LoginState) {
    OutlinedTextField(
        value = state.email,
        label = { Text(text = "Email") },
        onValueChange = { state.emailChanged(it) },
        isError = state.emailHasError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DoNotHaveAnAccount(navController: NavController) {
    Row {
        Text(text = "Don't have an account?")
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "Sing up",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable(true) {
                navController.navigate(AuthScreens.Register.route)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun EmailSingInPreview() {
    ReadsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Login(
                authViewModel = AuthViewModel(),
                navController = rememberNavController(),
                signInWithGoogle = {}
            )
        }
    }
}