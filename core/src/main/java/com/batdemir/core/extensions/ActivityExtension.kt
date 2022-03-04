package com.batdemir.core.extensions

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import com.batdemir.core.R


fun Activity.move(
    to: Class<*>,
    isKeepHistory: Boolean = false,
    bundle: Bundle? = null
) {
    val intent = Intent(this, to)
    if (bundle != null) intent.putExtras(bundle)
    this.startActivity(intent)
    this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    if (!isKeepHistory) finish()
}

fun Activity.reset() {
    val packageManager: PackageManager = packageManager
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    val componentName = intent?.component
    val mainIntent: Intent = Intent.makeRestartActivityTask(componentName)
    this.startActivity(mainIntent)
    this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}