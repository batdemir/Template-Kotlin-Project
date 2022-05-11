package com.batdemir.abtest.scenario_v1.usage_1

import android.view.View

class MainActivityCVariant : MainActivityVariant {
    override fun getText(): String {
        return "C Variant"
    }

    override fun getTextVisibility(): Int {
        return View.VISIBLE
    }
}