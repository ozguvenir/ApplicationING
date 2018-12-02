package com.ridvan.applicationing.viewmodel

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */

data class ProjectData(
    var id: Long,
    var name: String,
    val fullName: String,
    val owner: String
) : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ProjectData> = object : Parcelable.Creator<ProjectData> {
            override fun createFromParcel(source: Parcel): ProjectData = ProjectData(source)
            override fun newArray(size: Int): Array<ProjectData?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(name)
        dest.writeString(fullName)
        dest.writeString(owner)
    }
}