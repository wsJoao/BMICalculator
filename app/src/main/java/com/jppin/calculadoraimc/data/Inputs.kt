package com.jppin.calculadoraimc.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Inputs(
    val peso: String,
    val altura: String
) : Parcelable



