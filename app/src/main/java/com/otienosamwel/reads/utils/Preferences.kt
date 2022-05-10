package com.otienosamwel.reads.utils

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Preferences @Inject constructor(@ApplicationContext context: Context) {
    private val preferences = context.getSharedPreferences("READS", Context.MODE_PRIVATE)

    companion object {
        private const val TOKEN_KEY = "TOKEN_KEY"
        private const val REFRESH_TOKEN_KEY = "REFRESH_TOKEN_KEY"
    }

    fun getToken(): String? {
        return preferences.getString(TOKEN_KEY, null)
    }

    fun setToken(token: String) {
        preferences.edit {
            putString(TOKEN_KEY, token)
        }
    }

    fun getRefreshToken(): String? {
        return preferences.getString(REFRESH_TOKEN_KEY, null)
    }

    fun setRefreshToken(token: String) {
        preferences.edit {
            putString(REFRESH_TOKEN_KEY, token)
        }
    }
}