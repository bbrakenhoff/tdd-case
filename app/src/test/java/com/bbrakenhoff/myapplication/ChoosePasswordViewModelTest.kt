package com.bbrakenhoff.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bbrakenhoff.myapplication.ui.main.ChoosePasswordViewModel
import com.bbrakenhoff.myapplication.ui.main.Message
import com.bbrakenhoff.myapplication.ui.main.PasswordChecker
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChoosePasswordViewModelTest {

    private lateinit var passwordCheckerMock: PasswordChecker

    private lateinit var choosePasswordViewModel: ChoosePasswordViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        passwordCheckerMock = mockk(relaxed = true)
        choosePasswordViewModel = ChoosePasswordViewModel(passwordCheckerMock)
        choosePasswordViewModel.message.observeForever {}
    }

    @Test
    fun `updateMessage() updates message when passwords are not equal`() {
        every { passwordCheckerMock.isPasswordValid() } returns true

        choosePasswordViewModel.chosenPassword.value = "Chosen1@"
        choosePasswordViewModel.confirmedPassword.value = "Confirmed1@"

        choosePasswordViewModel.updateMessage()

        assertThat(choosePasswordViewModel.message.value).isEqualTo(Message.ErrorPasswordsUnequal)
    }

    @Test
    fun `updateMessage() updates message when chosen password is not valid`() {
        every { passwordCheckerMock.isPasswordValid() } returns false

        choosePasswordViewModel.chosenPassword.value = "Chosen1@"
        choosePasswordViewModel.confirmedPassword.value = "Confirmed1@"

        choosePasswordViewModel.updateMessage()

        assertThat(choosePasswordViewModel.message.value).isEqualTo(Message.ErrorChosenPasswordInvalid)
    }

    @Test
    fun `updateMessage() updates message when passwords are equal and valid`() {
        every { passwordCheckerMock.isPasswordValid() } returns true

        choosePasswordViewModel.chosenPassword.value = "Chosen1@"
        choosePasswordViewModel.confirmedPassword.value = "Chosen1@"

        choosePasswordViewModel.updateMessage()

        assertThat(choosePasswordViewModel.message.value).isEqualTo(Message.SuccessPasswordConfirmed)
    }

    @Test
    fun `updateMessages() clears message when one of the passwords is empty`() {
        every { passwordCheckerMock.isPasswordValid() } returns true

        choosePasswordViewModel.chosenPassword.value = "Chosen1@"
        choosePasswordViewModel.confirmedPassword.value = "Chosen1@"

        choosePasswordViewModel.updateMessage()
        assertThat(choosePasswordViewModel.message.value).isEqualTo(Message.SuccessPasswordConfirmed)

        choosePasswordViewModel.chosenPassword.value = ""

        choosePasswordViewModel.updateMessage()

        assertThat(choosePasswordViewModel.message.value).isNull()
    }

    @Test
    fun `updateMessages() clears message when both passwords are empty`() {
        every { passwordCheckerMock.isPasswordValid() } returns true

        choosePasswordViewModel.chosenPassword.value = "Chosen1@"
        choosePasswordViewModel.confirmedPassword.value = "Chosen1@"

        choosePasswordViewModel.updateMessage()

        choosePasswordViewModel.chosenPassword.value = null
        choosePasswordViewModel.confirmedPassword.value = null

        choosePasswordViewModel.updateMessage()

        assertThat(choosePasswordViewModel.message.value).isNull()
    }
}
