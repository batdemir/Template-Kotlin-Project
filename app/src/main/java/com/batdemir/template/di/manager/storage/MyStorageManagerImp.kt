package com.batdemir.template.di.manager.storage

import android.content.Context
import javax.inject.Inject

class MyStorageManagerImp @Inject constructor(
    context: Context
) : MyStorageManager {
    private val sharedPreferences = context.getSharedPreferences("batdemir", Context.MODE_PRIVATE)

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            commit()
        }
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    override fun setInt(key: String, value: Int) {
        with(sharedPreferences.edit()) {
            putInt(key, value)
            commit()
        }
    }

    override fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, -1)
    }
}