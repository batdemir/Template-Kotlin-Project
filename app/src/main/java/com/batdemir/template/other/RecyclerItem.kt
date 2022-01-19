package com.batdemir.template.other

interface RecyclerItem {
    val id: Long
    var isSelected: Boolean
    override fun equals(other: Any?): Boolean
}