package com.batdemir.template.di.manager.resource

import android.content.Context
import android.content.res.Resources

interface MyResourceManager {
    fun getContext(): Context
    fun getResources(): Resources
}