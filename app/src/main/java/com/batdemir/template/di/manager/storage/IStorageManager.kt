package com.batdemir.template.di.manager.storage

interface IStorageManager {
    fun setString(key: String, value: String)
    fun getString(key: String): String
    fun setInt(key: String, value: Int)
    fun getInt(key: String): Int
}