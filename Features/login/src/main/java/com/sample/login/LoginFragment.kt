package com.sample.login

import com.base.presentation.ui.fragment.beforeLogin.login.LoginVM
import com.sample.base.BaseFragment
import com.sample.login.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginVM>() {

    override val layoutId: Int = R.layout.fragment_login
    override val viewModel: LoginVM by viewModel()
    override var backgroundResId: Int = com.sample.resources.R.drawable.ic_iphone_bg

    override fun prepareViews() {

    }




}

