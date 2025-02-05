package com.jppin.calculadoraimc.ui.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jppin.calculadoraimc.data.Inputs

class BmiViewModel : ViewModel() {
    val bmiData  = MutableLiveData<BmiInputs>()
    val inputData = MutableLiveData<Inputs>()

    fun updateInput(feet: String, inches: String, pounds: String) {
        Log.d("BmiViewModel", "updateInput called with feet: $feet, inches: $inches, pounds: $pounds")
        bmiData.value = BmiInputs(feet, inches, pounds)
    }
    fun bmiConversion(feet: String, inches: String, pounds: String) {
        val height = (feet.toDouble() * 0.3048) + (inches.toDouble() * 0.0254)
        val weight = pounds.toDouble() * 0.453592
        inputData.value = Inputs(weight.toString(), height.toString())
    }
    fun clearData() {
        bmiData .value = BmiInputs()
    }
}

data class BmiInputs(
    val feet: String = "",
    val inches: String = "",
    val pounds: String = "",
)