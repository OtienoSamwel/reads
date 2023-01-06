package com.otienosamwel.reads.ui.presentation.features.auth

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dsc.form_builder.TextFieldState
import com.otienosamwel.reads.R
import com.otienosamwel.reads.ui.presentation.components.SpaceLarge
import com.otienosamwel.reads.ui.presentation.components.SpaceMedium
import com.otienosamwel.reads.ui.presentation.components.SpaceSmall
import com.otienosamwel.reads.ui.presentation.features.auth.signIn.*
import com.otienosamwel.reads.ui.theme.ReadsTheme

@Composable
fun SignUp(navController: NavController, signInWithGoogle: () -> Unit) {
    val scrollState = rememberScrollState()
    val authViewModel: AuthViewModel = hiltViewModel()
    val state = authViewModel.signUpState

    val emailState = state.getState<TextFieldState>(name = SignUpStateConstants.EMAIL_STATE)
    val firstNameState =
        state.getState<TextFieldState>(name = SignUpStateConstants.FIRST_NAME_STATE)
    val lastNameState = state.getState<TextFieldState>(name = SignUpStateConstants.LAST_NAME_STATE)
    val passwordState = state.getState<TextFieldState>(name = SignUpStateConstants.PASSWORD_STATE)
    val confirmPasswordState =
        state.getState<TextFieldState>(name = SignUpStateConstants.PASSWORD_CONFIRM_STATE)

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
        FirstNameFiled(state = firstNameState)
        if (firstNameState.hasError) ErrorMessage(message = "first name is required")

        SpaceSmall()

        LastNameField(state = lastNameState)
        if (lastNameState.hasError) ErrorMessage(message = "last name is required")

        SpaceSmall()

        EmailField(state = emailState)
        if (emailState.hasError) ErrorMessage(message = "email not valid")

        SpaceSmall()

        ChoosePasswordField(state = passwordState, "Choose password")
        if (passwordState.hasError) ErrorMessage(message = "password not valid")

        SpaceSmall()

        ConfirmPasswordFiled(state = confirmPasswordState, "Confirm password")
        if (confirmPasswordState.hasError) ErrorMessage(message = "passwords don't match")

        SpaceMedium()

        SingUpButton(viewModel = authViewModel, navController = navController)

        SpaceSmall()

        AlreadyHaveAnAccount(navController = navController)

        SpaceMedium()

        Or()

        SpaceMedium()

        ContinueWithGoogle(onClick = signInWithGoogle)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoosePasswordField(state: TextFieldState, label: String) {

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(value = state.value,
        label = { Text(text = label) },
        onValueChange = { state.change(it) },
        isError = state.hasError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val imageIcon =
                if (passwordVisible) R.drawable.ic_visible else R.drawable.ic_not_visible

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painterResource(id = imageIcon), contentDescription = null)
            }
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPasswordFiled(state: TextFieldState, label: String) {

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(value = state.value,
        label = { Text(text = label) },
        onValueChange = { state.change(it) },
        isError = state.hasError,
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
        })
}

@Composable
fun AlreadyHaveAnAccount(navController: NavController) {
    Row {
        Text(text = "Already have an account?")
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "Sign in",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable(true) {
                navController.navigate(AuthScreens.Login.route)
            })
    }
}

@Composable
fun SingUpButton(viewModel: AuthViewModel, navController: NavController) {
    val context = LocalContext.current
    OutlinedButton(
        onClick = {
            if (viewModel.signUpState.validate() && !viewModel.isLoading.value) {
                viewModel.signUp(context) {
                    navController.navigate(AuthScreens.Login.route)
                }
            }
        },
        modifier = Modifier.fillMaxWidth(),
    ) {
        if (viewModel.isLoading.value) LoadingButtonAnimation()
        if (!viewModel.isLoading.value) Text(text = "Sign up")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstNameFiled(state: TextFieldState) {
    OutlinedTextField(
        value = state.value,
        label = { Text(text = "First name") },
        onValueChange = { state.change(it) },
        isError = state.hasError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LastNameField(state: TextFieldState) {
    OutlinedTextField(
        value = state.value,
        label = { Text(text = "Last name") },
        onValueChange = { state.change(it) },
        isError = state.hasError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()

    )
}

@Preview
@Composable
fun SignUpPreview() {
    ReadsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SignUp(navController = rememberNavController(), signInWithGoogle = {})
        }
    }
}
