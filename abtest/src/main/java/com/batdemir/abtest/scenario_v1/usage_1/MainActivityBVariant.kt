package com.batdemir.abtest.scenario_v1.usage_1

import android.view.View

class MainActivityBVariant : MainActivityVariant {
    override fun getText(): String {
        return "B Variant"
    }

    override fun getTextVisibility(): Int {
        return View.VISIBLE
    }
}