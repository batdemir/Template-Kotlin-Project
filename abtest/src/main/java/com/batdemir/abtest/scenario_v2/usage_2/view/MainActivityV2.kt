package com.batdemir.abtest.scenario_v2.usage_2.view

import com.batdemir.abtest.R
import com.batdemir.abtest.databinding.MainActivityV2Binding
import com.batdemir.abtest.engine.FirebaseEngine
import com.batdemir.abtest.scenario_v2.usage_2.MainActivityScenario
import com.batdemir.abtest.z_other.MainViewModel
import com.batdemir.core.core.view.BaseActivity

class MainActivityV2 :
    BaseActivity<MainActivityV2Binding, MainViewModel>(R.layout.main_activity_v2) {
    private val abValue: Int = FirebaseEngine().getValue(FirebaseEngine.KEY_TWO, 0) as Int
    private val mScenario = MainActivityScenario(abValue)

    override fun setupData() {
        doTest()
    }

    private fun doTest() {
        supportFragmentManager
            .beginTransaction()
            .replace(getBinding().fragmentContainerView.id, mScenario.getFragment())
            .commit()
    }
}