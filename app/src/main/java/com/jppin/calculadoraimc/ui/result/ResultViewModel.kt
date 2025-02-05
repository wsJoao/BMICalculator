package com.jppin.calculadoraimc.ui.result

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {
    val resultsData: MutableLiveData<Results> by lazy {
        MutableLiveData<Results>()
    }

    @SuppressLint("DefaultLocale")
    fun imcData(weight: Double, height: Double, diagnosis: Diagnosis) {
        val imc = weight / (height * height)
        val imcFormatted = String.format("%.2f", imc)
        val diagnosisResult = when {
            imc < 18.5 -> diagnosis.underweight
            imc < 25 -> diagnosis.normal
            imc < 30 -> diagnosis.overweight
            else -> diagnosis.obesity
        }
        resultsData.value = Results(imcFormatted, diagnosisResult)
    }
}

data class Results(
    val imc: String,
    val diagnosis: String
)

data class Diagnosis (
    val underweight: String,
    val normal: String,
    val overweight: String,
    val obesity: String,
)