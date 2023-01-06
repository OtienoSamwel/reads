package com.otienosamwel.reads.ui.presentation.features.account

import androidx.lifecycle.ViewModel
import com.otienosamwel.reads.utils.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AccountViewModel @Inject constructor(
    val preferences: Preferences
) : ViewModel() {

    fun clearPreference() = preferences.clearPreferences()

}