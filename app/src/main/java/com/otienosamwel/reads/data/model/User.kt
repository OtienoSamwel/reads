package com.otienosamwel.reads.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: Int? = null,
    val username: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val email: String,
    @SerializedName("profile_photo_url") val profilePhotoUrl: String?
)