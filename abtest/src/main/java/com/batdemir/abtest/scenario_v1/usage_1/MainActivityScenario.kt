package com.batdemir.abtest.scenario_v1.usage_1

import com.batdemir.abtest.engine.FirebaseEngine
import com.batdemir.abtest.scenario_v1.BaseScenario
import timber.log.Timber
import kotlin.random.Random

class MainActivityScenario : BaseScenario<String>(
    engine = FirebaseEngine(),
    key = FirebaseEngine.KEY_ONE,
    defaultValue = "test"
), MainActivityVariant {
    private val aVariant: MainActivityAVariant = MainActivityAVariant()
    private val bVariant: MainActivityBVariant = MainActivityBVariant()
    private val cVariant: MainActivityCVariant = MainActivityCVariant()

    override fun getText(): String {
        Timber.d(value)
        return when (Random.nextInt(0, 10)) {
            in 0..4 -> aVariant.getText()
            in 4..7 -> bVariant.getText()
            else -> cVariant.getText()
        }

    }

    override fun getTextVisibility(): Int {
        Timber.d(value)
        return when (Random.nextInt(0, 10)) {
            in 0..4 -> aVariant.getTextVisibility()
            in 4..7 -> bVariant.getTextVisibility()
            else -> cVariant.getTextVisibility()
        }
    }
}