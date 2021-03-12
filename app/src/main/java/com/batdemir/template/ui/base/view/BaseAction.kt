package com.batdemir.template.ui.base.view

import android.os.Bundle

interface BaseAction {
    fun inject()
    fun setupDefinition(savedInstanceState: Bundle?)
    fun setupData()
    fun setupListener()
}