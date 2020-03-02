package com.base.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import com.base.R
import com.base.databinding.ActivityMainBinding
import com.sample.base.BaseActivity
import com.sample.base.BaseViewModel
import com.sample.utils.extentions_bindingAdapters.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    override val viewModel: BaseViewModel by viewModel()
    override val layoutId = R.layout.activity_main

    private var previousBackgroundResId = R.color.material_grey_100

    override fun initHandler() {
        binding.handler = this
    }

    override fun initViews() {

    }

    override fun changeBackground(resId: Int, isColor: Boolean) {
        if (previousBackgroundResId != resId) {
            binding.imgBackground.setImage(resId)
            previousBackgroundResId = resId
        }
    }

    /**
     *  Navigation'daki fragment degisim action'larini kullanabilmemi saglayan method.
     *  Opsiyonel olarak parametre alabilir
     */
    override fun navigateFragment(
        navAction: Int,
        bundle: Bundle?,
        navOptions: NavOptions?,
        extras: FragmentNavigator.Extras?
    ) {
        getCurrentFragment()?.let {
            NavHostFragment.findNavController(it).navigate(
                navAction,
                bundle, // Bundle of args
                navOptions, // NavOptions
                extras
            )
            return
        }
    }


    private fun getCurrentFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }

//    fun toolbarVisibility(visible: Boolean) {
//        binding.toolbar.visibleIf(visible)
//    }

    override fun showHideProgress(isShowing: Boolean) {
        binding.containerProgress.apply {
            when {
                isShowing -> startFadeIn()
                !isShowing && isVisible() -> startFadeOut()
                else -> setGone()
            }
        }
    }

}
