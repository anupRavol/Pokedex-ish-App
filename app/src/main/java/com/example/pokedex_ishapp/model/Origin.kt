package com.example.pokedex_ishapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Origin(
    val name: String?,
    val url: String?
): Parcelable