package com.batdemir.abtest.scenario_v1.usage_2

import androidx.fragment.app.Fragment
import com.batdemir.abtest.engine.FirebaseEngine
import com.batdemir.abtest.scenario_v1.BaseScenario
import timber.log.Timber
import kotlin.random.Random

class MainActivityScenario : BaseScenario<Int>(
    engine = FirebaseEngine(),
    key = FirebaseEngine.KEY_TWO,
    defaultValue = 0
), MainActivityVariant {
    private val aVariant: MainActivityAVariant = MainActivityAVariant()
    private val bVariant: MainActivityBVariant = MainActivityBVariant()

    override fun getFragment(): Fragment {
        Timber.d(value.toString())
        return when (Random.nextInt(0, 10)) {
            in 0..5 -> aVariant.getFragment()
            else -> bVariant.getFragment()
        }
    }
}