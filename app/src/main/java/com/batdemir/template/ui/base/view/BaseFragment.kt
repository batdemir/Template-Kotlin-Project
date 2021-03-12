package com.batdemir.template.ui.base.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.batdemir.template.R
import com.batdemir.template.ui.base.vm.BaseViewModel
import com.batdemir.template.ui.view.MainActivity
import com.google.android.material.progressindicator.LinearProgressIndicator
import javax.inject.Inject

abstract class BaseFragment<X : ViewDataBinding, V : BaseViewModel> constructor(
    private val layoutId: Int
) : Fragment(),
    BaseAction {
    @Inject
    lateinit var viewModel: V
    protected var binding: X? = null
    protected var progressBar: LinearProgressIndicator? = null

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<X>(
            layoutInflater,
            layoutId,
            container,
            false
        ).apply {
            this.lifecycleOwner = viewLifecycleOwner
        }
        setupDefinition(savedInstanceState)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar = (requireActivity() as MainActivity).progressBar
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
            is BaseViewModel.State.Nothing -> progressBar?.hide()
            is BaseViewModel.State.Error -> progressBar?.hide()
            is BaseViewModel.State.ShowLoading -> progressBar?.show()
            is BaseViewModel.State.ShowContent -> progressBar?.hide()
            is BaseViewModel.State.ShowError -> showError(state.throwable, state.requestType)
            is BaseViewModel.State.ShowDialog -> showDialog(state.message)
        }
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton(
                R.string.ok
            ) { dialog, _ -> dialog?.dismiss() }
    }

    private fun showError(throwable: Throwable, requestType: BaseViewModel.RequestType) {
        progressBar?.hide()
        when (requestType) {
            BaseViewModel.RequestType.ACTION -> generateActionRequestError(throwable.message ?: "")
            BaseViewModel.RequestType.INIT -> generateInitRequestError(throwable.message ?: "")
        }
    }

    private fun generateActionRequestError(message: String) {
        onStateChanged(BaseViewModel.State.ShowContent)
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton(
                R.string.ok
            ) { dialog, _ -> dialog?.dismiss() }
    }

    private fun generateInitRequestError(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton(
                R.string.ok
            ) { dialog, _ ->
                run {
                    dialog?.dismiss()
                    requireActivity().onBackPressed()
                }
            }
    }
}