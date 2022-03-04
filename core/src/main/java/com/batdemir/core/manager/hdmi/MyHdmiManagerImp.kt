package com.batdemir.core.manager.hdmi

import android.content.Context
import android.content.IntentFilter
import android.hardware.display.DisplayManager
import android.view.Display
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class MyHdmiManagerImp @Inject constructor(
    @ApplicationContext private val context: Context
) : MyHdmiReceiver.HdmiOnStateListener,
    MyHdmiManager {
    private val myHdmiReceiver: MyHdmiReceiver = MyHdmiReceiver()
    private val displayManager: DisplayManager =
        context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    private val displays: MutableLiveData<List<Display>?> = MutableLiveData()
    private val presentationDisplay: MutableLiveData<Display?> = MutableLiveData()
    private val state: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }

    init {
        myHdmiReceiver.setListener(this)
    }

    override fun onStateChange(state: Boolean) {
        this.state.postValue(state)
        Thread.sleep(100)
        val mDisplays = displayManager.displays.toList()
        displays.postValue(mDisplays)
        presentationDisplay.postValue(mDisplays.firstOrNull { display -> display.name != DEFAULT_DISPLAY_NAME })
        hdmiConnectionChanged()
    }

    override fun hdmiConnectionChanged() {
        Timber.d("Hdmi connection has been changed.")
    }

    override fun hasHdmiConnection(): MutableLiveData<Boolean> {
        return state
    }

    override fun getDisplays(): MutableLiveData<List<Display>?> {
        return displays
    }

    override fun getPresentationDisplay(): MutableLiveData<Display?> {
        return presentationDisplay
    }

    override fun register() {
        val filter = IntentFilter()
        filter.addAction(HDMI_INTENT)
        context.registerReceiver(myHdmiReceiver, filter)
    }

    override fun unRegister() {
        context.unregisterReceiver(myHdmiReceiver)
    }

    companion object {
        private const val HDMI_INTENT = "android.intent.action.HDMI_PLUGGED"
        private const val DEFAULT_DISPLAY_NAME = "Built-in Screen"
    }
}