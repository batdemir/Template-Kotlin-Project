package com.batdemir.template.di.initializer

import com.batdemir.template.app.MyApplication

interface Initializer {
    fun initialize(application: MyApplication)
}