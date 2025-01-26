package com.jppin.calculadoraimc.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Inputs(
    val bodyWeight: String,
    val height: String
) : Parcelable



