package com.batdemir.template.ui.view.home

import android.view.Display
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.batdemir.template.di.manager.hdmi.MyHdmiManager
import com.batdemir.template.ui.base.vm.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val myHdmiManager: MyHdmiManager
) : BaseViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "UHD Photo"
    }
    private val _textTwo = MutableLiveData<String>().apply {
        value = "UHD Photo 2"
    }
    val text: LiveData<String> = _text
    val textTwo: LiveData<String> = _textTwo

    fun hdmiConnectionChanged() {
        myHdmiManager.hdmiConnectionChanged()
    }

    fun hasHdmiConnection(): MutableLiveData<Boolean> {
        return myHdmiManager.hasHdmiConnection()
    }

    fun getDisplays(): MutableLiveData<List<Display>?> {
        return myHdmiManager.getDisplays()
    }

    fun getPresentationDisplay(): MutableLiveData<Display?> {
        return myHdmiManager.getPresentationDisplay()
    }

    fun register() {
        myHdmiManager.register()
    }

    fun unRegister() {
        myHdmiManager.unRegister()
    }
}