package com.jppin.calculadoraimc.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import androidx.annotation.StringRes
import javax.inject.Inject

class StringProvider @Inject constructor(@ApplicationContext private val context: Context) {
    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }
}