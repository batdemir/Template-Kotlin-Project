package com.batdemir.template.ui.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<X : ViewDataBinding> constructor(
    private val layoutId: Int
) : AppCompatActivity(),
    BaseAction {
    protected var binding: X? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        setupDefinition(savedInstanceState)
        setupData()
        setupListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}