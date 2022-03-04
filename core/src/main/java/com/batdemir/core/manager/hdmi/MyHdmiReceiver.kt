package com.batdemir.core.manager.hdmi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyHdmiReceiver : BroadcastReceiver() {
    private var listener: HdmiOnStateListener? = null

    fun setListener(listener: HdmiOnStateListener) {
        this.listener = listener
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action == HDMI_INTENT) {
            val state = intent.getBooleanExtra(STATE, false)
            listener?.onStateChange(state)
        }
    }

    interface HdmiOnStateListener {
        fun onStateChange(state: Boolean)
    }

    companion object {
        private const val HDMI_INTENT = "android.intent.action.HDMI_PLUGGED"
        private const val STATE = "state"
    }
}