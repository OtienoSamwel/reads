package com.otienosamwel.reads.utils

import android.content.Context
import android.widget.Toast
import com.dsc.form_builder.BaseState
import com.dsc.form_builder.FormState

/**
 * extend toast to work with context, will not work on a thread that is not main
 */

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}