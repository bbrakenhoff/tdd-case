package com.bbrakenhoff.myapplication

import com.bbrakenhoff.myapplication.ui.main.PasswordChecker
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class PasswordCheckerTest {

    private lateinit var passwordChecker: PasswordChecker

    @Before
    fun before(){
        passwordChecker= PasswordChecker()
    }

    @Test
    fun `isValid() returns false when password is shorter than min length`() {
        passwordChecker. password = "Achm3@"
        assertThat(passwordChecker.isPasswordValid()).isFalse()
    }


    @Test
    fun `isValid() returns false when password contains does not contain any digits`() {
         passwordChecker. password  = "Achme@Test"
        assertThat(passwordChecker.isPasswordValid()).isFalse()
    }

    @Test
    fun `isValid() returns false when password does not contain a capital letter`() {
         passwordChecker. password  = "achm3@test"
        assertThat(passwordChecker.isPasswordValid()).isFalse()
    }

    @Test
    fun `isValid() returns false when password does not contain a lowercase letter`() {
         passwordChecker. password  = "ACHM3TEST"
        assertThat(passwordChecker.isPasswordValid()).isFalse()
    }

    @Test
    fun `isValid() returns false when password does not contain a valid punctuation char`() {
         passwordChecker. password  = "Achm3aTest"
        assertThat(passwordChecker.isPasswordValid()).isFalse()
    }

    @Test
    fun `isValid() returns false when password contains a char other than digits, capital- lowercase letter and punctuaction char`() {
         passwordChecker. password  = "Achm3@Test?"
        assertThat(passwordChecker.isPasswordValid()).isFalse()
    }

    @Test
    fun `isValid() returns false when password contains whitespaces`() {
         passwordChecker. password  = "Achm3@ test"
        assertThat(passwordChecker.isPasswordValid()).isFalse()
    }

    @Test
    fun `isValid(password) returns true when password complies to all rules`() {
         passwordChecker. password  = "Achm@TddW0rksh0p!"
        assertThat(passwordChecker.isPasswordValid()).isTrue()
    }
}
