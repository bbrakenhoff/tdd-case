package com.bbrakenhoff.myapplication.ui.main

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.bbrakenhoff.myapplication.R

enum class Message(@ColorRes val colorResId: Int, @StringRes val messageResId: Int) {
    SuccessPasswordConfirmed(R.color.colorAccentGreen, R.string.message_success_password_chosen),
    ErrorChosenPasswordInvalid(R.color.colorAccentRed, R.string.message_error_password_invalid),
    ErrorPasswordsUnequal(R.color.colorAccentRed, R.string.message_error_passwords_unequal)
}
