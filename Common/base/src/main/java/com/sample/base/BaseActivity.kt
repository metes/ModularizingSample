package com.sample.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator

abstract class BaseActivity<BindingType : ViewDataBinding, ViewModelType : BaseViewModel> :
    AppCompatActivity() {

    lateinit var binding: BindingType
    protected abstract val viewModel: ViewModelType
    protected abstract val layoutId: Int

    abstract fun initHandler()
    abstract fun initViews()
    abstract fun showHideProgress(isShow: Boolean)
    abstract fun changeBackground(resId: Int, color: Boolean)
    abstract fun navigateFragment(
        navAction: Int,
        bundle: Bundle? = null,
        navOptions: NavOptions? = null,
        extras: FragmentNavigator.Extras? = null
    )

    var onBackPressAlternative: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        initHandler()
        initViews()
    }

    override fun onBackPressed() {
        if (onBackPressAlternative != null) {
            onBackPressAlternative!!()
        } else {
            super.onBackPressed()
        }
    }


}