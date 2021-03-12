package com.batdemir.template.di.manager.resource

import android.content.Context
import android.content.res.Resources
import javax.inject.Inject

class MyResourceManagerImp @Inject constructor(
    context: Context
) : MyResourceManager {
    private val _context = context

    override fun getContext(): Context {
        return _context
    }

    override fun getResources(): Resources {
        return _context.resources
    }
}