package com.base.di

import com.base.activity.MainActivityVM
import com.base.presentation.ui.fragment.beforeLogin.login.LoginVM
import com.base.presentation.ui.fragment.beforeLogin.loginSignUpDecision.DecisionVM
import com.sample.network.APIClient
import com.sample.splash.SplashVM
import com.sample.utils.helpers.SharedPrefHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  Koin (D.I.) icin gerekli olan modullerin tanimlanmasi
 */
object Modules {

    val mainActivityModule = module {
        viewModel { MainActivityVM(get(), get()) }
        single { SharedPrefHelper(get()) }
        single { APIClient() }
    }
    val decisionModule = module {
        viewModel { DecisionVM(get(), get()) }
    }
    val splashModule = module {
        viewModel { SplashVM(get(), get()) }
    }
    val loginModule = module {
        viewModel { LoginVM(get(), get()) }
    }


}
