package com.batdemir.abtest.engine

interface ABTestEngine {
    fun getValue(key: String, defaultValue: Any): Any
}