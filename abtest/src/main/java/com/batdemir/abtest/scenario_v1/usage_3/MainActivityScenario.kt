package com.batdemir.abtest.scenario_v1.usage_3

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
    private val cVariant: MainActivityCVariant = MainActivityCVariant()

    override fun getViewId(): Int {
        Timber.d(value.toString())
        return when (Random.nextInt(0, 10)) {
            in 0..4 -> aVariant.getViewId()
            in 4..7 -> bVariant.getViewId()
            else -> cVariant.getViewId()
        }
    }
}