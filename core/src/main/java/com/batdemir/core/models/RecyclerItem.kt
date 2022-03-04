package com.batdemir.core.models

interface RecyclerItem {
    val id: Long
    var isSelected: Boolean
    override fun equals(other: Any?): Boolean
}