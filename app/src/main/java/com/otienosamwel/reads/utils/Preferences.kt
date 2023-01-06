package com.otienosamwel.reads.utils

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Preferences @Inject constructor(@ApplicationContext context: Context) {
    private val preferences = context.getSharedPreferences("READS", Context.MODE_PRIVATE)

    private companion object {
        private const val TOKEN_KEY = "TOKEN_KEY"
        private const val REFRESH_TOKEN_KEY = "REFRESH_TOKEN_KEY"
        private const val HAS_SEEN_ON_BOARDING_KEY = "HAS_SEEN_ON_BOARDING_KEY"
        private const val GOOGLE_ID_TOKEN_KEY = "GOOGLE_ID_TOKEN_KEY"
    }

    fun getToken(): String? {
        return preferences.getString(TOKEN_KEY, "")
    }

    fun setToken(token: String) {
        preferences.edit {
            putString(TOKEN_KEY, token)
        }
    }

    fun getRefreshToken(): String? {
        return preferences.getString(REFRESH_TOKEN_KEY, "")
    }

    fun setRefreshToken(token: String) {
        preferences.edit {
            putString(REFRESH_TOKEN_KEY, token)
        }
    }

    fun getGoogleIdToken(): String? {
        return preferences.getString(GOOGLE_ID_TOKEN_KEY, null)
    }

    fun setGoogleIdToken(token: String) {
        preferences.edit {
            putString(GOOGLE_ID_TOKEN_KEY, token)
        }
    }

    fun hasSeenOnBoarding(): Boolean {
        return preferences.getBoolean(HAS_SEEN_ON_BOARDING_KEY, true)
    }

    fun setSeenOnBoarding() {
        preferences.edit {
            putBoolean(HAS_SEEN_ON_BOARDING_KEY, true)
        }
    }

    fun clearPreferences() {
        preferences.edit().clear().apply()
    }
}