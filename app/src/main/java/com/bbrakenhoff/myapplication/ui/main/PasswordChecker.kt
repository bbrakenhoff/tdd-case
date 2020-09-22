package com.bbrakenhoff.myapplication.ui.main

class PasswordChecker() {

    companion object {
        private const val AllowedPasswordLength = 8
        private const val AllowedPunctuationChars = "!@#$%*_"
    }

    var password: String = ""

    fun isPasswordValid(): Boolean {
        return isAllowedLength() && containsDigit() &&
            containsCapitalLetter() && containsLowercaseLetter() &&
            containsPunctuationChar() && onlyContainsAllowedChars()
    }

    private fun isAllowedLength() = password.length >= AllowedPasswordLength

    private fun containsCapitalLetter() = password.contains(Regex("[A-Z]"))

    private fun containsLowercaseLetter() = password.contains(Regex("[a-z]"))

    private fun containsPunctuationChar() = password.any {
        AllowedPunctuationChars.contains(it)
    }

    private fun containsDigit() = password.contains(Regex(".*\\d.*"))

    private fun onlyContainsAllowedChars() = password.toCharArray().all {
        it.isDigit() || it.isLowerCase() || it.isUpperCase() || AllowedPunctuationChars.contains(
            it
        )
    }
}
