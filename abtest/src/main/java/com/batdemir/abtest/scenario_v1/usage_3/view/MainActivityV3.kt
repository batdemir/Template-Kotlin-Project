package com.batdemir.abtest.scenario_v1.usage_3.view

import com.batdemir.abtest.R
import com.batdemir.abtest.databinding.MainActivityV3Binding
import com.batdemir.abtest.scenario_v1.usage_3.MainActivityScenario
import com.batdemir.abtest.z_other.MainViewModel
import com.batdemir.core.core.view.BaseActivity

class MainActivityV3 :
    BaseActivity<MainActivityV3Binding, MainViewModel>(R.layout.main_activity_v3) {
    private val mScenario = MainActivityScenario()

    override fun setupData() {
        doTest()
    }

    override fun setupListener() {
        getBinding().textView.setOnClickListener {
        }
    }

    private fun doTest() {
        getBinding().viewStub.viewStub?.let {
            it.layoutResource = mScenario.getViewId()
            it.inflate()
        }
    }
}