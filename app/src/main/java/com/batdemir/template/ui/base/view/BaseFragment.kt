package com.batdemir.template.ui.base.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.batdemir.template.ui.base.vm.BaseViewModel
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel> constructor(
    private val layoutId: Int
) : Fragment(),
    BaseAction {
    @Inject
    lateinit var viewModel: V
    private var binding: B? = null

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<B>(layoutInflater, layoutId, container, false).apply {
            this.lifecycleOwner = viewLifecycleOwner
        }
        setupDefinition(savedInstanceState)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupData()
        setupListener()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun setupData() {
        viewModel.baseLiveData.observe(viewLifecycleOwner, ::onStateChanged)
    }

    private fun onStateChanged(state: BaseViewModel.State) {
        when (state) {
            is BaseViewModel.State.Error -> (requireActivity() as BaseActivity<*, *>).error()
            is BaseViewModel.State.ShowLoading -> (requireActivity() as BaseActivity<*, *>).showLoading(state.requestType)
            is BaseViewModel.State.ShowContent -> (requireActivity() as BaseActivity<*, *>).showContent(state.requestType)
            is BaseViewModel.State.ShowError -> (requireActivity() as BaseActivity<*, *>).showError(
                state.requestType,
                state.throwable
            )
            is BaseViewModel.State.ShowDialog -> (requireActivity() as BaseActivity<*, *>).showDialog(state.message)
        }
    }

    fun getBinding(): B {
        return if (binding != null) binding!! else throw NullPointerException("Expression 'binding' must not be null")
    }
}