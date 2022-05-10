package com.otienosamwel.reads.ui.presentation.features.auth.signIn

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.otienosamwel.reads.ui.presentation.components.SpaceAbsolute
import com.otienosamwel.reads.ui.presentation.components.SpaceLarge
import com.otienosamwel.reads.ui.presentation.components.SpaceSmall
import com.otienosamwel.reads.ui.theme.ReadsTheme

@Composable
fun SignIn() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeText()

        SpaceAbsolute()

        SignInWithGoogleButton()
        SpaceSmall()
        SignInWithEmailButton()

        SpaceLarge()
    }

}

@Composable
fun SignInWithGoogleButton() {
    OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
        Icon(imageVector = Icons.Rounded.LocationOn, contentDescription = "")
        SpaceSmall()
        Text("Continue with Google")
    }
}

@Composable
fun SignInWithEmailButton() {
    OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
        Icon(imageVector = Icons.Rounded.Email, contentDescription = "")
        SpaceSmall()
        Text(text = "Continue with Email")
    }
}

@Composable
fun WelcomeText() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Text(
            text = "Welcome to reads",
            style = MaterialTheme.typography.displayMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SignInPreview() {
    ReadsTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            SignIn()
        }
    }
}