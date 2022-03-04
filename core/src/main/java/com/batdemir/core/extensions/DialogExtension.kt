package com.batdemir.core.extensions

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.annotation.StringRes
import com.batdemir.core.R
import kotlinx.coroutines.suspendCancellableCoroutine

suspend fun AlertDialog.await(
    showPositiveButton: Boolean = true,
    showNegativeButton: Boolean = false,
    @StringRes positiveButtonText: Int = R.string.ok,
    @StringRes negativeButtonText: Int = R.string.cancel
) = suspendCancellableCoroutine<Boolean> { cont ->
    val listener = DialogInterface.OnClickListener { _, which ->
        if (which == AlertDialog.BUTTON_POSITIVE) cont.resume(true, null)
        else if (which == AlertDialog.BUTTON_NEGATIVE) cont.resume(false, null)
    }
    if (showPositiveButton)
        setButton(AlertDialog.BUTTON_POSITIVE, context.getString(positiveButtonText), listener)
    if (showNegativeButton)
        setButton(AlertDialog.BUTTON_NEGATIVE, context.getString(negativeButtonText), listener)
    setOnCancelListener { cont.cancel() }
    cont.invokeOnCancellation { dismiss() }
    show()
}