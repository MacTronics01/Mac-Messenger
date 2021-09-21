package com.example.macmessenger.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val uid: String,
    val name: String,
    val profileImageUrl: String?
) :Parcelable {
    constructor() : this("", "", "")


    }

