package com.batdemir.template.core.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.batdemir.template.R
import com.batdemir.template.core.vm.BaseViewModel
import com.batdemir.template.utils.observe
import timber.log.Timber

abstract class BaseActivity<B : ViewDataBinding, V : BaseViewModel> constructor(
    private val layoutId: Int
) : AppCompatActivity(),
    BaseAction {
    private var viewModel: V? = null
    private var binding: B? = null
    private var progressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        setupDefinition(savedInstanceState)
        setupData()
        setupListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        viewModel = null
    }

    override fun setupData() {
        viewModel?.let {
            observe(it.baseLiveData, ::onStateChanged)
        }
    }

    private fun onStateChanged(state: BaseViewModel.State) {
        when (state) {
            is BaseViewModel.State.Error -> error()
            is BaseViewModel.State.ShowLoading -> showLoading(state.requestType)
            is BaseViewModel.State.ShowContent -> showContent(state.requestType)
            is BaseViewModel.State.ShowError -> showError(
                state.requestType,
                state.throwable
            )
            is BaseViewModel.State.ShowDialog -> showDialog(state.message)
            is BaseViewModel.State.Empty -> {
                Timber.d("State Empty")
            }
        }
    }

    private fun generateActionRequestError(message: String) {
        AlertDialog
            .Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                viewModel?.let {
                    it.baseLiveData.value = BaseViewModel.State.Empty
                }
                dialog.dismiss()
            }
            .show()
    }

    private fun generateInitRequestError(message: String) {
        AlertDialog
            .Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                viewModel?.let {
                    it.baseLiveData.value = BaseViewModel.State.Empty
                }
                dialog.dismiss()
                onBackPressed()
            }
            .show()
    }

    private fun showProgress() {
        if (progressDialog == null) {
            progressDialog = Dialog(this, R.style.ThemeOverlay_MaterialComponents_Dialog).apply {
                setCancelable(false)
                setContentView(R.layout.view_progress)
            }
        }
        if (progressDialog?.isShowing == false) {
            progressDialog?.show()
        }
    }

    private fun dismissProgress() {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    fun setupViewModel(viewModel: V) {
        this.viewModel = viewModel
    }

    fun showLoading(requestType: BaseViewModel.RequestType) {
        if (requestType == BaseViewModel.RequestType.ACTION) showProgress()
        else if (requestType == BaseViewModel.RequestType.INIT) showProgress()
    }

    fun showContent(requestType: BaseViewModel.RequestType) {
        if (requestType == BaseViewModel.RequestType.ACTION || requestType == BaseViewModel.RequestType.INIT) dismissProgress()
    }

    fun showDialog(message: String) {
        AlertDialog.Builder(baseContext)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
    }

    fun showError(requestType: BaseViewModel.RequestType, throwable: Throwable?) {
        dismissProgress()
        if (requestType == BaseViewModel.RequestType.ACTION) generateActionRequestError(
            throwable?.message ?: ""
        )
        else if (requestType == BaseViewModel.RequestType.INIT) generateInitRequestError(
            throwable?.message ?: ""
        )
    }

    fun error() {
        dismissProgress()
    }

    fun getBinding(): B {
        return binding ?: throw NullPointerException("Expression 'binding' must not be null")
    }

    fun getViewModel(): V {
        return viewModel
            ?: throw  java.lang.NullPointerException("Expression 'view model' must not be null")
    }
}