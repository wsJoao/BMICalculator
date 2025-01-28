package com.jppin.calculadoraimc.data

import android.content.Intent

object IntentProcessor {
    fun extractInputs(intent: Intent): Inputs? {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("input_data", Inputs::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("input_data")
        }
    }
}