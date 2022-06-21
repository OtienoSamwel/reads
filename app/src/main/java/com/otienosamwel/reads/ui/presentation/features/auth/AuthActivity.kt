package com.otienosamwel.reads.ui.presentation.features.auth

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.otienosamwel.reads.R
import com.otienosamwel.reads.ui.presentation.features.auth.signIn.Login
import com.otienosamwel.reads.ui.presentation.features.auth.signIn.SignUp
import com.otienosamwel.reads.ui.theme.ReadsTheme
import com.otienosamwel.reads.utils.Preferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient
    private val googleSignInResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                val idToken = task.result.idToken
                idToken?.let { token ->
                    preferences.setGoogleIdToken(token)
                    authViewModel.singInWithGoogle(token)

                }
                //todo send token to server for jwt authentication
            } catch (e: ApiException) {
                e.printStackTrace()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReadsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AuthNavigation(
                        authViewModel = authViewModel,
                        signInWithGoogle = this::singInWithGoogle
                    )
                }
            }
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        Log.i(TAG, "onCreate: ${preferences.getGoogleIdToken()}")
    }

    private fun singInWithGoogle() {
        val intent = googleSignInClient.signInIntent
        googleSignInResultLauncher.launch(intent)
    }


    private companion object {
        private val TAG = AuthActivity::class.java.simpleName
    }
}

@Composable
fun AuthNavigation(authViewModel: AuthViewModel, signInWithGoogle: () -> Unit) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Login") {
        composable(AuthScreens.Login.route) {
            Login(navController = navController, signInWithGoogle = signInWithGoogle)
        }
        composable(AuthScreens.Register.route) {
            SignUp(
                navController = navController,
                signInWithGoogle = signInWithGoogle
            )
        }
    }
}

sealed class AuthScreens(val route: String) {
    object Login : AuthScreens(route = "Login")
    object Register : AuthScreens(route = "Register")
}