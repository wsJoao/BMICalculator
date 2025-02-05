package com.jppin.calculadoraimc.data

import android.content.Intent
import androidx.fragment.app.Fragment
import com.jppin.calculadoraimc.ui.result.ResultActivity

object NavigationHelper {
    fun navigateToResult(fragment: Fragment, inputs: Inputs) {
        val context = fragment.requireActivity()
        val openResults = Intent(context, ResultActivity::class.java).apply {
            putExtra("input_data", inputs)
        }
        context.startActivity(openResults)
    }
}