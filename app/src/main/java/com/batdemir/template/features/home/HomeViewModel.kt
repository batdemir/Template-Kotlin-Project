package com.batdemir.template.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.batdemir.core.manager.hdmi.MyHdmiManager
import com.batdemir.core.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
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