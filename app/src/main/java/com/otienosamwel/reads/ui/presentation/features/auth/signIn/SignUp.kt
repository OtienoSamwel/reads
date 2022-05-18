package com.otienosamwel.reads.ui.presentation.features.auth.signIn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reads.R
import com.otienosamwel.reads.ui.presentation.components.SpaceLarge
import com.otienosamwel.reads.ui.presentation.components.SpaceMedium
import com.otienosamwel.reads.ui.presentation.components.SpaceSmall
import com.otienosamwel.reads.ui.presentation.features.auth.AuthScreens
import com.otienosamwel.reads.ui.presentation.features.auth.AuthViewModel
import com.otienosamwel.reads.ui.theme.ReadsTheme
import com.otienosamwel.reads.utils.toast

@Composable
fun SignUp(state: SignUpState, navController: NavController, signInWithGoogle: () -> Unit) {
    val scrollState = rememberScrollState()
    val authViewModel: AuthViewModel = viewModel()

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .scrollable(state = scrollState, orientation = Orientation.Vertical),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WelcomeText()

        SpaceLarge()
        SpaceMedium()

        Text(
            text = "Sign up",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium
        )

        SpaceMedium()

        EmailField(state = state)
        if (state.emailHasError) ErrorMessage(message = "email not valid")

        SpaceSmall()

        ChoosePasswordField(state = state, "Choose password")
        if (state.passwordHasError) ErrorMessage(message = "password not valid")

        SpaceSmall()

        ChoosePasswordField(state = state, "Confirm password")
        if (state.passwordHasError) ErrorMessage(message = "passwords don't match")

        SpaceMedium()

        SingUpButton(viewModel = authViewModel, state = state)

        SpaceSmall()

        AlreadyHaveAnAccount(navController = navController)

        SpaceMedium()

        Or()

        SpaceMedium()

        ContinueWithGoogle(onClick = signInWithGoogle)
    }
}

@Composable
fun ChoosePasswordField(state: SignUpState, label: String) {

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = state.password,
        label = { Text(text = label) },
        onValueChange = { state.passwordChanged(it) },
        isError = state.passwordConfirmationError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
        ),
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val imageIcon =
                if (passwordVisible) R.drawable.ic_visible else R.drawable.ic_not_visible

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painterResource(id = imageIcon), contentDescription = null)
            }
        }
    )
}

@Composable
fun AlreadyHaveAnAccount(navController: NavController) {
    Row {
        Text(text = "Already have an account?")
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "Sign up",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable(true) {
                navController.navigate(AuthScreens.Login.route)
            })
    }
}

@Composable
fun SingUpButton(viewModel: AuthViewModel, state: SignUpState) {
    val context = LocalContext.current

    OutlinedButton(
        onClick = { if (state.validate()) context.toast("Email and passcode validated") },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Sign Up")
    }
}

@Composable
fun EmailField(state: SignUpState) {
    OutlinedTextField(
        value = state.email,
        label = { Text(text = "Email") },
        onValueChange = { state.emailChanged(it) },
        isError = state.emailHasError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
        ),
        modifier = Modifier
            .fillMaxWidth()
    
    )
}


@Preview
@Composable
fun SignUpPreview() {
    ReadsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SignUp(
                state = remember { SignUpState() },
                navController = rememberNavController(),
                signInWithGoogle = {}
            )
        }
    }
}
