package com.otienosamwel.reads.ui.presentation.features.splash


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.otienosamwel.reads.ui.presentation.features.auth.AuthActivity
import com.otienosamwel.reads.ui.presentation.features.main.MainActivity
import com.otienosamwel.reads.utils.Preferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EntryActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        val activity =
            if (!preferences.getToken().isNullOrBlank()) {
                MainActivity::class.java
            } else if (preferences.getToken().isNullOrBlank()) {
                AuthActivity::class.java
            } else {
                AuthActivity::class.java
            }
        startActivity(Intent(this, activity))
    }
}