package com.jppin.calculadoraimc.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jppin.calculadoraimc.data.Inputs

class ImcViewModel : ViewModel() {
    val inputData = MutableLiveData<Inputs>()

    fun updateInput(weight: String, height: String) {
        inputData.value = Inputs(weight, height)
    }

    fun clearData() {
        inputData.value = Inputs()
    }
}