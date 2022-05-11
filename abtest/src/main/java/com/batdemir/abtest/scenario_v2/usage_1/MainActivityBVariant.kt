package com.batdemir.abtest.scenario_v2.usage_1

import android.view.View

class MainActivityBVariant (private val value: String) : MainActivityVariant {
    override fun getText(): String {
        return "B Variant"
    }

    override fun getTextVisibility(): Int {
        return View.VISIBLE
    }
}