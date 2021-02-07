package com.batdemir.template.di.manager.resource

import android.content.Context
import android.content.res.Resources
import javax.inject.Inject

class ResourceManager @Inject constructor(
    context: Context
) : IResourceManager {
    private val _context = context

    override fun getContext(): Context {
        return _context
    }

    override fun getResources(): Resources {
        return _context.resources
    }
}