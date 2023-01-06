package com.otienosamwel.reads.ui.presentation.features.auth

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators


object AuthState {
    val loginState = FormState(
        fields = listOf(
            TextFieldState(
                name = AuthStateConstants.EMAIL_STATE,
                validators = listOf(Validators.Email())
            ),
            TextFieldState(
                name = AuthStateConstants.PASSWORD_STATE,
                validators = listOf(Validators.Required())
            )
        )
    )

    val singUpState = FormState(
        fields = listOf(
            TextFieldState(
                name = SignUpStateConstants.EMAIL_STATE,
                validators = listOf(Validators.Email())
            ),
            TextFieldState(
                name = SignUpStateConstants.PASSWORD_STATE,
                validators = listOf(Validators.Required())
            ),
            TextFieldState(
                name = SignUpStateConstants.FIRST_NAME_STATE,
                validators = listOf(Validators.Required())
            ),
            TextFieldState(
                name = SignUpStateConstants.LAST_NAME_STATE,
                validators = listOf(Validators.Required())
            ),
            TextFieldState(
                name = SignUpStateConstants.PASSWORD_CONFIRM_STATE,
                validators = listOf(Validators.Required())
            )
        )
    )

    val passwordResetState = FormState(
        fields = listOf(
            TextFieldState(
                name = AuthStateConstants.EMAIL_STATE,
                validators = listOf(Validators.Required(), Validators.Email())
            )
        )
    )
}

object AuthStateConstants {
    const val EMAIL_STATE = "email"
    const val PASSWORD_STATE = "password"
}

object SignUpStateConstants {
    const val EMAIL_STATE = "email"
    const val PASSWORD_STATE = "password"
    const val FIRST_NAME_STATE = "firstName"
    const val LAST_NAME_STATE = "lastName"
    const val PASSWORD_CONFIRM_STATE = "passwordConfirm"
}