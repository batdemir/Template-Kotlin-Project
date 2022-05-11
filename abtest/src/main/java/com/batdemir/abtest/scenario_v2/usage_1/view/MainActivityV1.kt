package com.batdemir.abtest.scenario_v2.usage_1.view

import com.batdemir.abtest.R
import com.batdemir.abtest.databinding.MainActivityV1Binding
import com.batdemir.abtest.engine.FirebaseEngine
import com.batdemir.abtest.scenario_v2.usage_1.MainActivityScenario
import com.batdemir.abtest.scenario_v2.usage_2.view.MainActivityV2
import com.batdemir.abtest.z_other.MainViewModel
import com.batdemir.core.core.view.BaseActivity
import com.batdemir.core.extensions.move

class MainActivityV1 :
    BaseActivity<MainActivityV1Binding, MainViewModel>(R.layout.main_activity_v1) {
    private val abValue: String =
        FirebaseEngine().getValue(FirebaseEngine.KEY_ONE, "test") as String
    private val mScenario = MainActivityScenario(abValue)

    override fun setupData() {
        doTest()
    }

    override fun setupListener() {
        getBinding().textView.setOnClickListener {
            move(MainActivityV2::class.java, isKeepHistory = true)
        }
    }

    private fun doTest() {
        getBinding().textView.text = mScenario.getText()
        getBinding().textView.visibility = mScenario.getTextVisibility()
    }
}