package com.batdemir.core.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.batdemir.core.R
import com.batdemir.core.extensions.observe
import com.batdemir.core.vm.BaseViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<B : ViewDataBinding, V : BaseViewModel> constructor(
    private val layoutId: Int,
    @BottomSheetBehavior.State private val state: Int = BottomSheetBehavior.STATE_EXPANDED
) : BottomSheetDialogFragment(), BaseAction {
    private var viewModel: V? = null
    private var binding: B? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate<B>(
                layoutInflater,
                layoutId,
                container,
                false
            ).apply {
                this.lifecycleOwner = viewLifecycleOwner
            }
            setupDefinition(savedInstanceState)
            setupData()
            setupListener()
        }
        return getBinding().root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val bottomSheet =
                (it as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            bottomSheet?.let { frameLayout ->
                val layoutParams = frameLayout.layoutParams
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
                frameLayout.layoutParams = layoutParams
                with(BottomSheetBehavior.from(frameLayout)) {
                    this.state = this@BaseBottomSheetDialogFragment.state
                }
            }
        }
        return dialog
    }

    override fun setupData() {
        viewModel?.let {
            observe(it.baseLiveData, ::onStateChanged)
        }
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }

    private fun onStateChanged(state: BaseViewModel.State) {
        when (state) {
            is BaseViewModel.State.Error -> (requireActivity() as BaseActivity<*, *>).error()
            is BaseViewModel.State.ShowLoading -> (requireActivity() as BaseActivity<*, *>).showLoading(
                state.requestType
            )
            is BaseViewModel.State.ShowContent -> (requireActivity() as BaseActivity<*, *>).showContent(
                state.requestType
            )
            is BaseViewModel.State.ShowError -> {
                (requireActivity() as BaseActivity<*, *>).showError(
                    state.requestType,
                    state.throwable
                )
                viewModel?.let {
                    it.baseLiveData.value = BaseViewModel.State.Empty
                }
            }
            is BaseViewModel.State.ShowDialog -> {
                (requireActivity() as BaseActivity<*, *>).showDialog(
                    state.message
                )
                viewModel?.let {
                    it.baseLiveData.value = BaseViewModel.State.Empty
                }
            }
            is BaseViewModel.State.Empty -> {
                (requireActivity() as BaseActivity<*, *>).dismissProgress()
            }
        }
    }

    fun setupViewModel(viewModel: V) {
        this.viewModel = viewModel
    }

    fun getBinding(): B {
        return binding ?: throw NullPointerException("Expression 'binding' must not be null")
    }

    fun getViewModel(): V {
        return viewModel
            ?: throw  java.lang.NullPointerException("Expression 'view model' must not be null")
    }
}