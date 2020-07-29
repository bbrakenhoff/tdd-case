package com.bbrakenhoff.myapplication

import com.bbrakenhoff.myapplication.ui.main.ChoosePasswordViewModel
import com.bbrakenhoff.myapplication.ui.main.PasswordChecker
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule {

    fun start(): Module =
        module {
            factory { PasswordChecker() }

            viewModel { ChoosePasswordViewModel(get()) }
        }
}
