package com.otienosamwel.reads.ui.presentation.features.auth.signIn

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dsc.form_builder.TextFieldState
import com.otienosamwel.reads.R
import com.otienosamwel.reads.ui.presentation.components.LoginButton
import com.otienosamwel.reads.ui.presentation.components.Sizes

@Composable
fun WelcomeText() {
    Text(
        text = "Welcome to Reads",
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.headlineLarge
    )
}

@Composable
fun ContinueWithGoogle(onClick: () -> Unit) {
    LoginButton(
        text = "Continue with google",
        onClick = onClick, icon = R.drawable.ic_google_logo
    )
}

@Preview(showBackground = true)
@Composable
fun Or() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .width(150.dp)
                .height(.5.dp)
        )
        Text(
            text = "Or",
            modifier = Modifier.padding(Sizes.medium),
            style = MaterialTheme.typography.labelMedium
        )
        Divider(
            modifier = Modifier
                .width(150.dp)
                .height(.5.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(state: TextFieldState) {

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = state.value,
        label = { Text(text = "Password") },
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
        }, maxLines = 1
    )
}

@Composable
fun ErrorMessage(message: String) {
    Text(
        text = message,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun LoadingButtonAnimation() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_bubbles))
    LottieAnimation(
        composition = composition,
        iterations = Int.MAX_VALUE,
        modifier = Modifier
            .padding(0.dp)
            .size(24.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(state: TextFieldState) {
    OutlinedTextField(
        value = state.value,
        label = { Text(text = "Email") },
        onValueChange = { state.change(it) },
        isError = state.hasError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth(), maxLines = 1
    )
}





