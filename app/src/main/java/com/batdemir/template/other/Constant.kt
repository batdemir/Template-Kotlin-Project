package com.batdemir.template.other

object Constant {
    const val APP_DB = "batdemir.db"
    const val TIMEOUT = 120L
    const val START_PAGE_INDEX = 1L
    const val NETWORK_PAGE_SIZE = 30
    const val DELAY = 2000L
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