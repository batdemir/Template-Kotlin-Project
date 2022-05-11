package com.batdemir.abtest.scenario_v2.usage_2

import androidx.fragment.app.Fragment
import com.batdemir.abtest.scenario_v2.usage_2.view.MainFragmentV2A

class MainActivityAVariant(private val value: Int) : MainActivityVariant {
    override fun getFragment(): Fragment {
        return MainFragmentV2A()
    }
}