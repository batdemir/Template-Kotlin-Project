package com.batdemir.template.ui.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.batdemir.template.di.manager.hdmi.MyHdmiManager
import com.batdemir.template.ui.base.vm.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val myHdmiManager: MyHdmiManager
) : BaseViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "UHD Photo"
    }
    private val _textTwo = MutableLiveData<String>().apply {
        value = "UHD Photo 2"
    }
    val text: LiveData<String> = _text
    val textTwo: LiveData<String> = _textTwo
}