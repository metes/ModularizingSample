package com.sample.decision

import com.base.presentation.ui.fragment.beforeLogin.loginSignUpDecision.DecisionVM
import com.sample.base.BaseFragment
import com.sample.decision.databinding.FragmentDecisionBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DecisionFragment : BaseFragment<FragmentDecisionBinding, DecisionVM>() {

    override val layoutId: Int = R.layout.fragment_decision
    override val viewModel: DecisionVM by viewModel()
    override var backgroundResId: Int = com.sample.resources.R.drawable.ic_iphone_bg

    override fun prepareViews() {

    }

    override fun initBindingVariables() {
        binding.viewModel = viewModel
    }

    override fun subscribe() {
        binding.txtSignUp.setFunction {
            navigateFragment(com.sample.navigation.R.id.nav_action_decision_login)
        }
    }



}

