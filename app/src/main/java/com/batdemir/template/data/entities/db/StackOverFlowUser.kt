package com.batdemir.template.data.entities.db

import com.google.gson.annotations.SerializedName

data class StackOverFlowUser(
    @SerializedName("badge_counts")
    val stackOverFlowBadgeCounts: StackOverFlowBadgeCounts,
    @SerializedName("account_id")
    val accountId: Long,
    @SerializedName("is_employee")
    val isEmployee: Boolean,
    @SerializedName("last_access_date")
    val lastAccessDate: Long,
    @SerializedName("reputation_change_year")
    val reputationChangeYear: Long,
    @SerializedName("reputation_change_quarter")
    val reputationChangeQuarter: Long,
    @SerializedName("reputation_change_month")
    val reputationChangeMonth: Long,
    @SerializedName("reputation_change_week")
    val reputationChangeWeek: Long,
    @SerializedName("reputation_change_day")
    val reputationChangeDay: Long,
    val reputation: Long,
    @SerializedName("creation_date")
    val creationDate: Long,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("user_id")
    val userId: Long,
    val link: String,
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("display_name")
    val displayName: String
)