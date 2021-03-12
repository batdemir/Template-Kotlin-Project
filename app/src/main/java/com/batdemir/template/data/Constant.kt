package com.batdemir.template.data

object Constant {
    const val TIMEOUT = 120.toLong()
}

enum class StackOverFlowOrderType(val value: String) {
    DESC("desc"),
    ASC("asc")
}

enum class StackOverFlowSortType(val value: String) {
    REPUTATION("reputation"),
    CREATION("creation"),
    NAME("name"),
    MODIFIED("modified")
}