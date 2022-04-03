package com.batdemir.core.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.batdemir.core.core.vm.BaseViewModel
import com.batdemir.core.extensions.observe

abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel> constructor(
    private val layoutId: Int
) : Fragment(),
    BaseAction {
    private var viewModel: V? = null
    private var binding: B? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate<B>(layoutInflater, layoutId, container, false).apply {
                this.lifecycleOwner = viewLifecycleOwner
            }
            setupDefinition(savedInstanceState)
            setupData()
            setupListener()
        }
        return getBinding().root
    }

    override fun setupData() {
        viewModel?.let {
            observe(it.baseLiveData, ::onStateChanged)
        }
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
        return viewModel ?: throw java.lang.NullPointerException("Expression 'view model' must not be null")
    }
}
