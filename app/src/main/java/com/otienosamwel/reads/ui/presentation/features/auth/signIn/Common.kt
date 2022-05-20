package com.otienosamwel.reads.ui.presentation.features.auth.signIn

import androidx.compose.foundation.clickable
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
    LoginButton(text = "Continue with google", onClick = onClick, icon = R.drawable.ic_google_logo)
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


@Composable
fun PasswordField(state: LoginState) {

    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = state.password,
        label = { Text(text = "Password") },
        onValueChange = { state.passwordChanged(it) },
        isError = state.passwordHasError,
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
fun ErrorMessage(message: String) {
    Text(
        text = message,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.fillMaxWidth()
    )
}


