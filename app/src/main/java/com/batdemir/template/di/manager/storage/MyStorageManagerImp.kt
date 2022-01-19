package com.batdemir.template.di.manager.storage

import android.content.Context
import com.batdemir.template.other.Constant
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MyStorageManagerImp @Inject constructor(
    @ApplicationContext context: Context
) : MyStorageManager {
    private val sharedPreferences =
        context.getSharedPreferences(Constant.APP_STORAGE, Context.MODE_PRIVATE)

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