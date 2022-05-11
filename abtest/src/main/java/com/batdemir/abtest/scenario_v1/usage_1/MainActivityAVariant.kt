package com.batdemir.abtest.scenario_v1.usage_1

import android.view.View

class MainActivityAVariant : MainActivityVariant {
    override fun getText(): String {
        return "A Variant"
    }

    override fun getTextVisibility(): Int {
        return View.VISIBLE
    }
}