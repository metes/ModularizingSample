package com.sample.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator

import org.jetbrains.anko.support.v4.alert
import timber.log.Timber


abstract class BaseFragment<BindingType : ViewDataBinding, out ViewModelType: BaseViewModel> : Fragment() {

    var TAG = this.javaClass.simpleName

    /**
     * ViewDataBinding tipindeki generic type, child'dan gonderiliyor.
     */
    lateinit var binding: BindingType
    /**
     * BaseViewModel tipindeki generic type, child'dan gonderiliyor
      */
    abstract val viewModel: ViewModelType
    /**
     * layoutId, child'da tanimlaniyor
      */
    protected abstract val layoutId: Int
    /**
     *  Fragment'a ozel view duzenlemeleri burada yapiliyor
     */
    abstract fun prepareViews()

    private var baseActivity: BaseActivity<*, *>? = null

    open val backgroundResId: Int = 0 //R.color.material_grey_100


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        baseActivity = activity as BaseActivity<*, *>?
        prepareViews()
        prepareHandler()
        subscribe()
        subscribeErrors()

        return binding.root
    }

    private fun prepareHandler() {
        binding.lifecycleOwner = this
        initBindingVariables()
    }

    override fun onStart() {
        super.onStart()
        setOnBackPressed(null) // Add this
        changeFragmentBackground(backgroundResId)
    }

    override fun onStop() {
        baseActivity?.showHideProgress(false)
        super.onStop()
    }

    open fun setOnBackPressed(onBackAlternative: (() -> Unit)?) {
        (activity as BaseActivity<*, *>).onBackPressAlternative = onBackAlternative
    }

    /**
     *  XML icerisindeki degiskenin tanimlanmasi
     */
    open fun initBindingVariables() {
        Timber.e("Not have a binding variable")
    }

    private fun subscribeErrors() {
        viewModel.networkErrorDetection.observeThis {
            alert {
                titleResource = R.string.hata
                message = it
                positiveButton(android.R.string.ok) { it.dismiss() }
            }.show()
        }
        viewModel.loadingDetection.observeThis {
            timber("viewModel.loadingDetection: $it")
            baseActivity?.showHideProgress(it)
        }
    }

    private fun changeFragmentBackground(resId: Int, isColor: Boolean = false) {
        baseActivity?.changeBackground(resId, isColor)
    }

    open fun subscribe() {
        timber("Not have a subscription")
    }

    open fun onBackAlternative(): (() -> Unit)? {
        timber("Not have a special onBack function")
        return null
    }

    @SuppressLint("BinaryOperationInTimber", "LogNotTimber")
    fun timber(message: String, objectsAr: Array<String>? = null) {
        Timber.d("TimberLog: $message")
    }

    fun setFullScreen(isFullScreen: Boolean) {
        val flags = if (isFullScreen) {
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        } else {
            View.SYSTEM_UI_FLAG_VISIBLE
        }
        activity?.window?.decorView?.systemUiVisibility = flags
    }

    fun hideKeyboard() {
        context?.let {
            val imm = ContextCompat.getSystemService(it, InputMethodManager::class.java)
            imm?.hideSoftInputFromWindow(view?.rootView?.windowToken, 0)
        }
    }

    open fun navigateFragment(
        navAction: Int,
        bundle: Bundle? = null,
        navOptions: NavOptions? = null,
        extras: FragmentNavigator.Extras? = null
    ) {
        baseActivity?.navigateFragment(navAction, bundle, navOptions, extras)
    }


    fun <T>LiveData<T>.observeThis(function: (T) -> Unit) {
        observe(viewLifecycleOwner, Observer {
            it?.let {
                function(it)
            }
        })
    }


}
