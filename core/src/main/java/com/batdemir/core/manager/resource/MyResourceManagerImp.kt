package com.batdemir.core.manager.resource

import android.content.Context
import android.content.res.Resources
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MyResourceManagerImp @Inject constructor(
    @ApplicationContext context: Context
) : MyResourceManager {
    private val _context = context
    override fun getContext(): Context {
        return _context
    }

    override fun getResources(): Resources {
        return _context.resources
    }
}
