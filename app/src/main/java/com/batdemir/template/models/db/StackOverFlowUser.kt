package com.batdemir.template.models.db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.batdemir.core.models.RecyclerItem
import com.google.gson.annotations.SerializedName

@Entity(tableName = "StackOverFlowUser")
data class StackOverFlowUser(
    @SerializedName("account_id")
    @PrimaryKey
    @ColumnInfo(name = "stack_over_flow_id") override var id: Long,
    @ColumnInfo(name = "stack_over_flow_is_selected") override var isSelected: Boolean,
    @SerializedName("badge_counts")
    @Embedded var stackOverFlowBadgeCounts: StackOverFlowBadgeCounts? = null,
    @SerializedName("is_employee")
    @ColumnInfo(name = "stack_over_flow_is_employee") var isEmployee: Boolean? = null,
    @SerializedName("last_access_date")
    @ColumnInfo(name = "stack_over_flow_last_access_date") var lastAccessDate: Long? = null,
    @SerializedName("reputation_change_year")
    @ColumnInfo(name = "stack_over_flow_reputation_change_year") var reputationChangeYear: Long? = null,
    @SerializedName("reputation_change_quarter")
    @ColumnInfo(name = "stack_over_flow_reputation_change_quarter") var reputationChangeQuarter: Long? = null,
    @SerializedName("reputation_change_month")
    @ColumnInfo(name = "stack_over_flow_reputation_change_month") var reputationChangeMonth: Long? = null,
    @SerializedName("reputation_change_week")
    @ColumnInfo(name = "stack_over_flow_reputation_change_week") var reputationChangeWeek: Long? = null,
    @SerializedName("reputation_change_day")
    @ColumnInfo(name = "stack_over_flow_reputation_change_day") var reputationChangeDay: Long? = null,
    @ColumnInfo(name = "stack_over_flow_reputation") var reputation: Long? = null,
    @SerializedName("creation_date")
    @ColumnInfo(name = "stack_over_flow_creation_date") var creationDate: Long? = null,
    @SerializedName("user_type")
    @ColumnInfo(name = "stack_over_flow_user_type") var userType: String? = null,
    @SerializedName("user_id")
    @ColumnInfo(name = "stack_over_flow_user_id") var userId: Long? = null,
    @ColumnInfo(name = "stack_over_flow_link") var link: String? = null,
    @SerializedName("profile_image")
    @ColumnInfo(name = "stack_over_flow_profile_image") var profileImage: String? = null,
    @SerializedName("display_name")
    @ColumnInfo(name = "stack_over_flow_display_name") var displayName: String? = null,
) : RecyclerItem, Parcelable {
    constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        isSelected = parcel.readByte() != 0.toByte(),
        stackOverFlowBadgeCounts = parcel.readParcelable(StackOverFlowBadgeCounts::class.java.classLoader),
        isEmployee = parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        lastAccessDate = parcel.readValue(Long::class.java.classLoader) as? Long,
        reputationChangeYear = parcel.readValue(Long::class.java.classLoader) as? Long,
        reputationChangeQuarter = parcel.readValue(Long::class.java.classLoader) as? Long,
        reputationChangeMonth = parcel.readValue(Long::class.java.classLoader) as? Long,
        reputationChangeWeek = parcel.readValue(Long::class.java.classLoader) as? Long,
        reputationChangeDay = parcel.readValue(Long::class.java.classLoader) as? Long,
        reputation = parcel.readValue(Long::class.java.classLoader) as? Long,
        creationDate = parcel.readValue(Long::class.java.classLoader) as? Long,
        userType = parcel.readString(),
        userId = parcel.readValue(Long::class.java.classLoader) as? Long,
        link = parcel.readString(),
        profileImage = parcel.readString(),
        displayName = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeByte(if (isSelected) 1 else 0)
        parcel.writeParcelable(stackOverFlowBadgeCounts, flags)
        parcel.writeValue(isEmployee)
        parcel.writeValue(lastAccessDate)
        parcel.writeValue(reputationChangeYear)
        parcel.writeValue(reputationChangeQuarter)
        parcel.writeValue(reputationChangeMonth)
        parcel.writeValue(reputationChangeWeek)
        parcel.writeValue(reputationChangeDay)
        parcel.writeValue(reputation)
        parcel.writeValue(creationDate)
        parcel.writeString(userType)
        parcel.writeValue(userId)
        parcel.writeString(link)
        parcel.writeString(profileImage)
        parcel.writeString(displayName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StackOverFlowUser> {
        override fun createFromParcel(parcel: Parcel): StackOverFlowUser {
            return StackOverFlowUser(parcel)
        }

        override fun newArray(size: Int): Array<StackOverFlowUser?> {
            return arrayOfNulls(size)
        }
    }
}
