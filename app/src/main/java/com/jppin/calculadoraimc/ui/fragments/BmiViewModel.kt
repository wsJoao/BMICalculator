package com.jppin.calculadoraimc.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jppin.calculadoraimc.R
import com.jppin.calculadoraimc.data.Inputs
import com.jppin.calculadoraimc.utils.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(private val stringProvider: StringProvider) : ViewModel() {
    private val _inputData = MutableLiveData<Inputs>()
    val inputData: LiveData<Inputs> get() = _inputData

    private val _errors = MutableLiveData<Triple<String?, String?, String?>>()
    val errors: LiveData<Triple<String?, String?, String?>> get() = _errors

    private val _bmiData = MutableLiveData<BmiInputs>()

    fun updateInput(feet: String, inches: String, pounds: String) {
        _bmiData.value = BmiInputs(feet, inches, pounds)
    }

    fun bmiConversion(feet: String, inches: String, pounds: String) {
        val height = (feet.toDouble() * 0.3048) + (inches.toDouble() * 0.0254)
        val weight = pounds.toDouble() * 0.453592
        _inputData.value = Inputs(weight.toString(), height.toString())
    }

    fun checkFields(feet: String, inches: String, pounds: String){
        val feetError = if (feet.isBlank() || feet == ".")
            stringProvider.getString(R.string.err_empty_feet) else null
        val inchesError = if (inches.isBlank() || inches == ".")
            stringProvider.getString(R.string.err_empty_inches) else null
        val poundsError = if (pounds.isBlank() || pounds == ".")
            stringProvider.getString(R.string.err_empty_pounds) else null

        _errors.value = Triple(feetError, inchesError, poundsError)
    }
}

data class BmiInputs(
    val feet: String = "",
    val inches: String = "",
    val pounds: String = "",
)