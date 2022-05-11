package com.batdemir.abtest.scenario_v2.usage_1

import timber.log.Timber
import kotlin.random.Random

class MainActivityScenario(
    private val value: String
) : MainActivityVariant {
    private val aVariant: MainActivityAVariant = MainActivityAVariant(value)
    private val bVariant: MainActivityBVariant = MainActivityBVariant(value)
    private val cVariant: MainActivityCVariant = MainActivityCVariant(value)

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