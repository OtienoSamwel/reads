package com.otienosamwel.reads.ui.presentation.features.auth.signIn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.otienosamwel.reads.ui.presentation.components.SpaceLarge
import com.otienosamwel.reads.ui.presentation.components.SpaceMedium
import com.otienosamwel.reads.ui.presentation.components.SpaceSmall
import com.otienosamwel.reads.ui.presentation.features.auth.AuthActivity
import com.otienosamwel.reads.ui.presentation.features.auth.AuthScreens
import com.otienosamwel.reads.ui.presentation.features.auth.AuthViewModel

@Composable
fun Login(navController: NavController, signInWithGoogle: () -> Unit) {
    val scrollState = rememberScrollState()

    val authViewModel: AuthViewModel = hiltViewModel()
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

        SingInButton(viewModel = authViewModel)

        SpaceSmall()

        DoNotHaveAnAccount(navController = navController)

        SpaceMedium()

        Or()

        SpaceMedium()

        ContinueWithGoogle(onClick = signInWithGoogle)
    }
}


@Composable
fun SingInButton(viewModel: AuthViewModel) {
    val context = LocalContext.current as AuthActivity

    OutlinedButton(
        onClick = {
            if (viewModel.loginState.validate() && !viewModel.loginState.isLoginLoading) {
                viewModel.signInWithEmail(
                    viewModel.loginState.email,
                    viewModel.loginState.password,
                    context
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        if (!viewModel.loginState.isLoginLoading) Text(text = "Sign In")
        if (viewModel.loginState.isLoginLoading) LoadingButtonAnimation()
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
        Text(text = "Sign up",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable(true) {
                navController.navigate(AuthScreens.Register.route)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun EmailSingInPreview() {
}