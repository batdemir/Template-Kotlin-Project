package com.batdemir.core.core.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.batdemir.core.R
import com.batdemir.core.core.vm.BaseViewModel
import com.batdemir.core.extensions.await
import com.batdemir.core.extensions.observe
import kotlinx.coroutines.launch

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
            is BaseViewModel.State.ShowError -> {
                showError(
                    state.requestType,
                    state.throwable
                )
                viewModel?.let {
                    it.baseLiveData.value = BaseViewModel.State.Empty
                }
            }
            is BaseViewModel.State.ShowDialog -> {
                showDialog(state.message)
                viewModel?.let {
                    it.baseLiveData.value = BaseViewModel.State.Empty
                }
            }
            is BaseViewModel.State.Empty -> {
                dismissProgress()
            }
        }
    }

    private suspend fun generateActionRequestError(message: String) {
        AlertDialog
            .Builder(this)
            .setMessage(message)
            .create()
            .await()
    }

    private suspend fun generateInitRequestError(message: String) {
        AlertDialog
            .Builder(this)
            .setMessage(message)
            .create()
            .await()
        onBackPressed()
    }

    private fun showProgress() {
        if (progressDialog == null) {
            progressDialog = Dialog(this).apply {
                setCancelable(false)
                setContentView(R.layout.view_progress)
            }
        }
        if (progressDialog?.isShowing == false) {
            progressDialog?.show()
        }
    }

    fun dismissProgress() {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    fun showLoading(requestType: BaseViewModel.RequestType) {
        if (requestType == BaseViewModel.RequestType.ACTION) showProgress()
        else if (requestType == BaseViewModel.RequestType.INIT) showProgress()
    }

    fun showContent(requestType: BaseViewModel.RequestType) {
        if (requestType == BaseViewModel.RequestType.ACTION || requestType == BaseViewModel.RequestType.INIT) dismissProgress()
    }

    fun showDialog(message: String) {
        lifecycleScope.launch {
            generateActionRequestError(message)
        }
    }

    fun showError(requestType: BaseViewModel.RequestType, throwable: Throwable?) {
        dismissProgress()
        val message = throwable?.message ?: ""
        if (requestType == BaseViewModel.RequestType.ACTION) {
            lifecycleScope.launch {
                generateActionRequestError(message)
            }
        } else if (requestType == BaseViewModel.RequestType.INIT) {
            lifecycleScope.launch {
                generateInitRequestError(message)
            }
        }
    }

    fun setupViewModel(viewModel: V) {
        this.viewModel = viewModel
    }

    fun error() {
        dismissProgress()
    }

    fun getBinding(): B {
        return binding ?: throw NullPointerException("Expression 'binding' must not be null")
    }

    fun getViewModel(): V {
        return viewModel ?: throw java.lang.NullPointerException("Expression 'view model' must not be null")
    }
}
