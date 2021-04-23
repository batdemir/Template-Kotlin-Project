package com.batdemir.template.data

object Constant {
    const val TIMEOUT = 120.toLong()
    const val START_PAGE_INDEX: Long = 1
    const val NETWORK_PAGE_SIZE: Int = 30
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