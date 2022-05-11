package com.batdemir.abtest.scenario_v1.usage_2

import androidx.fragment.app.Fragment
import com.batdemir.abtest.scenario_v1.usage_2.view.MainFragmentV2B

class MainActivityBVariant : MainActivityVariant {
    override fun getFragment(): Fragment {
        return MainFragmentV2B()
    }
}