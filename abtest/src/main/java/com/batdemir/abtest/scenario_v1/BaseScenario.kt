package com.batdemir.abtest.scenario_v1

import com.batdemir.abtest.engine.ABTestEngine

abstract class BaseScenario<T : Any> constructor(
    engine: ABTestEngine,
    key: String,
    defaultValue: T
) {
    protected val value: T = engine.getValue(key, defaultValue) as T
}