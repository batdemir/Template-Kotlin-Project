package com.batdemir.abtest.scenario_v2.usage_2

import androidx.fragment.app.Fragment
import timber.log.Timber
import kotlin.random.Random

class MainActivityScenario(
    private val value: Int
) : MainActivityVariant {
    private val aVariant: MainActivityAVariant = MainActivityAVariant(value)
    private val bVariant: MainActivityBVariant = MainActivityBVariant(value)

    override fun getFragment(): Fragment {
        Timber.d(value.toString())
        return when (Random.nextInt(0, 10)) {
            in 0..5 -> aVariant.getFragment()
            else -> bVariant.getFragment()
        }
    }
}