package com.jppin.calculadoraimc.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Inputs(
    val weight: String = " ",
    val height: String = " ",
    val isValid: Boolean = false
) : Parcelable
