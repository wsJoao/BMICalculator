package com.jppin.calculadoraimc.data

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jppin.calculadoraimc.R
import kotlinx.parcelize.Parcelize

class MainViewModel : ViewModel() {
    val inputData: MutableLiveData<Inputs> by lazy {
        MutableLiveData<Inputs>()
    }

    fun updateInputs(weight: String, height: String, context: Context) {
        val weightError =
            if (weight.isBlank()) context.getString(R.string.error_peso_empty) else null
        val heightError =
            if (height.isBlank()) context.getString(R.string.error_altura_empty) else null

        val isValid = weightError == null && heightError == null
        inputData.value = Inputs(weight, height, isValid, weightError, heightError)
    }
    fun clearInputs() {
        inputData.value = Inputs()
    }
}

@Parcelize
data class Inputs(
    val weight: String = " ",
    val height: String = " ",
    val isValid: Boolean = false,
    val errorWeight: String? = null,
    val errorHeight: String? = null
) : Parcelable