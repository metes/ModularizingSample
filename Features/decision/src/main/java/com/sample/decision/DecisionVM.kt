package com.base.presentation.ui.fragment.beforeLogin.loginSignUpDecision

import android.app.Application
import com.sample.utils.components.SingleLiveEvent
import com.sample.network.APIClient

class DecisionVM(app: Application, client: com.sample.network.APIClient) : com.sample.base.BaseViewModel(app, client) {

    val onSignUpClicked = com.sample.utils.components.SingleLiveEvent<Boolean>()

    fun onSignUpClicked() {
        onSignUpClicked.value = true
    }
}