package com.bbrakenhoff.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bbrakenhoff.myapplication.R

class ChoosePasswordViewModel(private val passwordChecker: PasswordChecker) : ViewModel() {

    val chosenPassword = MutableLiveData("")
    val confirmedPassword = MutableLiveData("")

    private val _message = MutableLiveData<Message?>()
    val message: LiveData<Message?> get() = _message

    fun updateMessage() {
        passwordChecker.password = chosenPassword.value ?: ""
        _message.value = null

        if (bothPasswordsEntered()) {
            _message.value = Message.SuccessPasswordConfirmed

            if (!passwordsAreEqual()) {
                _message.value = Message.ErrorPasswordsUnequal
            } else if (!passwordChecker.isPasswordValid()) {
                _message.value = Message.ErrorChosenPasswordInvalid
            }
        }
    }

    private fun bothPasswordsEntered() =
        !chosenPassword.value.isNullOrBlank() && !confirmedPassword.value.isNullOrBlank()

    private fun passwordsAreEqual() = chosenPassword.value == confirmedPassword.value
}
