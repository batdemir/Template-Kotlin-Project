package com.batdemir.template.other

object Constant {
    const val APP_DB = "batdemir.db"
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