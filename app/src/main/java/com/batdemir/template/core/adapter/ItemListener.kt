package com.batdemir.template.core.adapter

import com.batdemir.template.other.RecyclerItem

interface ItemListener<T : RecyclerItem> {
    fun onClick(value: T)
    fun onLongClick(value: T)
}