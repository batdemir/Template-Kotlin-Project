package com.batdemir.abtest.scenario_v1.usage_3

import com.batdemir.abtest.R

class MainActivityAVariant : MainActivityVariant {
    override fun getViewId(): Int {
        return R.layout.custom_stub_a
    }
}