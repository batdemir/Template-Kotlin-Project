package com.batdemir.abtest.scenario_v2.usage_1

import android.view.View

class MainActivityAVariant(private val value: String) : MainActivityVariant {
    override fun getText(): String {
        return "A Variant"
    }

    override fun getTextVisibility(): Int {
        return View.VISIBLE
    }
}