package com.batdemir.abtest.engine

class LeanPlumEngine : ABTestEngine {
    private val dataSource: HashMap<String, Any> = hashMapOf<String, Any>().apply {
        this[KEY_ONE] = "lean_plum_test"
        this[KEY_TWO] = 999
    }

    override fun getValue(key: String, defaultValue: Any): Any {
        return try {
            dataSource[key] ?: defaultValue
        } catch (e: ClassCastException) {
            defaultValue
        }
    }

    companion object {
        const val KEY_ONE = "key_one"
        const val KEY_TWO = "key_two"
    }
}