package com.batdemir.template.models.db

import com.google.gson.annotations.SerializedName

data class StackOverFlowResponse(
    val items: List<StackOverFlowUser>,
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("quota_max")
    val quotaMax: Long,
    @SerializedName("quota_remaining")
    val quotaRemaining: Long
)