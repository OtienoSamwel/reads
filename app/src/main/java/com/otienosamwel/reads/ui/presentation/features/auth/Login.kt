package com.otienosamwel.reads.ui.presentation.features.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dsc.form_builder.TextFieldState
import com.otienosamwel.reads.ui.presentation.components.SpaceLarge
import com.otienosamwel.reads.ui.presentation.components.SpaceMedium
import com.otienosamwel.reads.ui.presentation.components.SpaceSmall
import com.otienosamwel.reads.ui.presentation.features.auth.signIn.*


@Composable
fun Login(navController: NavController, signInWithGoogle: () -> Unit) {
    val scrollState = rememberScrollState()
    val authViewModel: AuthViewModel = hiltViewModel()

    //state
    val emailState =
        authViewModel.loginState.getState<TextFieldState>(AuthStateConstants.EMAIL_STATE)
    val passwordState =
        authViewModel.loginState.getState<TextFieldState>(AuthStateConstants.PASSWORD_STATE)


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

        EmailField(state = emailState)
        if (emailState.hasError) ErrorMessage(message = "email not valid")

        SpaceSmall()

        PasswordField(state = passwordState)
        if (passwordState.hasError) ErrorMessage(message = "password cannot be empty")

        SpaceMedium()

        Text(
            "Forgot password?",
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    navController.navigate(AuthScreens.PasswordRest.route)
                },
            color = MaterialTheme.colorScheme.secondary
        )

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
            if (!viewModel.isLoading.value) {
                viewModel.signInWithEmail(context)
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        if (!viewModel.isLoading.value) Text(text = "Sign In")
        if (viewModel.isLoading.value) LoadingButtonAnimation()
    }
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