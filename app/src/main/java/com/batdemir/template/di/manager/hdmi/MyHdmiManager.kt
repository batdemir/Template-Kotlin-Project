package com.batdemir.template.di.manager.hdmi

import android.view.Display
import androidx.lifecycle.MutableLiveData

interface MyHdmiManager {
    fun hdmiConnectionChanged()
    fun hasHdmiConnection(): MutableLiveData<Boolean>
    fun getDisplays(): MutableLiveData<List<Display>?>
    fun getPresentationDisplay(): MutableLiveData<Display?>
    fun register()
    fun unRegister()
}