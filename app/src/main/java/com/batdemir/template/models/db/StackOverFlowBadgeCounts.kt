package com.batdemir.template.models.db

import android.os.Parcel
import android.os.Parcelable

data class StackOverFlowBadgeCounts(
    val bronze: Long,
    val silver: Long,
    val gold: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        bronze = parcel.readLong(),
        silver = parcel.readLong(),
        gold = parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(bronze)
        parcel.writeLong(silver)
        parcel.writeLong(gold)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StackOverFlowBadgeCounts> {
        override fun createFromParcel(parcel: Parcel): StackOverFlowBadgeCounts {
            return StackOverFlowBadgeCounts(parcel)
        }

        override fun newArray(size: Int): Array<StackOverFlowBadgeCounts?> {
            return arrayOfNulls(size)
        }
    }
}
