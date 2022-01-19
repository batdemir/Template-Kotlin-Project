package com.batdemir.template.data.entities.ui

import android.os.Parcel
import android.os.Parcelable
import com.batdemir.template.other.RecyclerItem

data class ActionItemModel(
    override val id: Long,
    val title: String? = null,
    val subTitle: String? = null,
    val iconRes: String? = null,
    val isEnabled: Boolean = true,
    val navigateUrl: String? = null,
    override var isSelected: Boolean = false
) : Parcelable, RecyclerItem {
    constructor(parcel: Parcel) : this(
        id = parcel.readLong(),
        title = parcel.readString(),
        subTitle = parcel.readString(),
        iconRes = parcel.readString(),
        isEnabled = parcel.readByte() != 0.toByte(),
        navigateUrl = parcel.readString(),
        isSelected = parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(subTitle)
        parcel.writeString(iconRes)
        parcel.writeByte(if (isEnabled) 1 else 0)
        parcel.writeString(navigateUrl)
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ActionItemModel> {
        override fun createFromParcel(parcel: Parcel): ActionItemModel {
            return ActionItemModel(parcel)
        }

        override fun newArray(size: Int): Array<ActionItemModel?> {
            return arrayOfNulls(size)
        }
    }
}
