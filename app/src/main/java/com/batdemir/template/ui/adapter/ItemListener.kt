package com.batdemir.template.ui.adapter

import com.batdemir.template.data.entities.RecyclerItem

interface ItemListener<T : RecyclerItem> {
    fun onClick(value: T)
    fun onLongClick(value: T)
}