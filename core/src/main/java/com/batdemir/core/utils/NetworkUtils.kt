package com.batdemir.core.utils

import android.content.Context
import android.webkit.WebSettings
import android.webkit.WebView

object NetworkUtil {
    fun getUserAgent(context: Context): String {
        return try {
            val constructor = WebSettings::class.java.getDeclaredConstructor(
                Context::class.java,
                WebView::class.java
            )
            constructor.isAccessible = true
            try {
                val settings = constructor.newInstance(context, null)
                settings.userAgentString
            } finally {
                constructor.isAccessible = false
            }
        } catch (e: Exception) {
            WebView(context).settings.userAgentString
        }
    }
}