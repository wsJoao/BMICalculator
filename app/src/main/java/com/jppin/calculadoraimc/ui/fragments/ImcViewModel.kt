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
class ImcViewModel @Inject constructor(private val stringProvider: StringProvider) : ViewModel() {
    private val _inputData = MutableLiveData<Inputs>()
    private val _errorData = MutableLiveData<Pair<String?, String?>>()
    val errorData: LiveData<Pair<String?, String?>> get() = _errorData

    fun updateInput(weight: String, height: String) {
        _inputData.value = Inputs(weight, height)
    }

    fun checkInputs(weight: String, height: String) {
        val weightError = if (weight.isBlank() || weight == ".")
            stringProvider.getString(R.string.err_weight_empty) else null

        val heightError = if (height.isBlank() || height == ".")
            stringProvider.getString(R.string.err_height_empty) else null

        _errorData.value = Pair(weightError, heightError)
    }
}