package com.ridvan.applicationing.persistence.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by ridvanozguvenir on 2.12.2018.
 */
@Entity
data class ProjectEntity(
    @SerializedName("id") @PrimaryKey var id: Long,
    @SerializedName("name") var name: String,
    @SerializedName("fullName") var fullName: String,
    @SerializedName("owner") var owner: String
) {

    constructor() : this(0L, "", "", "")
}