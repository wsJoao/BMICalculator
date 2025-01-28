package com.jppin.calculadoraimc.data

import android.content.Context
import android.content.Intent
import com.jppin.calculadoraimc.Result

object NavigationHelper {
    fun navigateToResult(context: Context, inputs: Inputs) {
        val openResult = Intent(context, Result::class.java).apply {
            putExtra("input_data", inputs)
        }
        context.startActivity(openResult)
    }
}