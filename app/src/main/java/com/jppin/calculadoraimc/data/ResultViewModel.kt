package com.jppin.calculadoraimc.data

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jppin.calculadoraimc.R

class ResultViewModel : ViewModel() {
    val resultData: MutableLiveData<Result> by lazy {
        MutableLiveData<Result>()
    }

    @SuppressLint("DefaultLocale")
    fun imcData(weight: Double, height: Double, context: Context) {
        val imc = weight / (height * height)
        val imcFormatted = String.format("%.2f", imc)
        val diagnosis = when {
            imc < 18.5 -> context.getString(R.string.diagBaixo)
            imc < 25 -> context.getString(R.string.diagNormal)
            imc < 30 -> context.getString(R.string.diagSobrepeso)
            else -> context.getString(R.string.diagObesidade)
        }
        resultData.value = Result(imcFormatted, diagnosis)
    }
}

data class Result(
    val imc: String,
    val diagnosis: String
)