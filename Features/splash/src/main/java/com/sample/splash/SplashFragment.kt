package com.sample.splash

import com.sample.base.BaseFragment
import com.sample.splash.databinding.FragmentSplashBinding
import com.sample.utils.extentions_bindingAdapters.splashAnimation
import org.koin.androidx.viewmodel.ext.android.viewModel

open class SplashFragment : BaseFragment<FragmentSplashBinding, SplashVM>() {

    override val layoutId: Int = R.layout.fragment_splash
    override val viewModel: SplashVM by viewModel()
    override val backgroundResId: Int = 0 //R.color.colorPrimary

    override fun prepareViews() {
        binding.imageViewLogo.splashAnimation(::onLogoAnimationEnd)
    }

    override fun onStart() {
        super.onStart()
        setFullScreen(true)
    }

    override fun onStop() {
        super.onStop()
        setFullScreen(false)
    }
    /**
     *  Animasyon bitiminde calistirilacak
     */
    private fun onLogoAnimationEnd() {
        navigateFragment(com.sample.navigation.R.id.nav_action_splash_to_decision)
    }


}

