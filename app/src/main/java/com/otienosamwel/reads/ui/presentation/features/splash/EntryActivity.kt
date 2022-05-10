package com.otienosamwel.reads.ui.presentation.features.splash


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.otienosamwel.reads.ui.presentation.features.main.MainActivity

class EntryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        startActivity(Intent(this, MainActivity::class.java))
    }
}