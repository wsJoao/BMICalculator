package com.jppin.calculadoraimc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jppin.calculadoraimc.data.Inputs
import com.jppin.calculadoraimc.data.MainViewModel
import com.jppin.calculadoraimc.data.NavigationHelper.navigateToResult
import com.jppin.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setObservers()
        setListeners()
    }
    private fun setObservers() {
        viewModel.inputData.observe(this) { inputData ->
            binding.etWeight.setText(inputData.weight)
            binding.etHeight.setText(inputData.height)

            binding.tilWeight.error = inputData.errorWeight
            binding.tilHeight.error = inputData.errorHeight

        }
    }
    private fun setListeners(){
        binding.btnCalculate.setOnClickListener {
            val weight = binding.etWeight.text.toString()
            val height = binding.etHeight.text.toString()

            viewModel.updateInputs(weight, height, this)

            if(viewModel.inputData.value?.isValid == true){
                val inputs = Inputs(weight, height)
                navigateToResult(this, inputs)
                viewModel.clearInputs()
            }
        }
    }
}

