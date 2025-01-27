package com.jppin.calculadoraimc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jppin.calculadoraimc.data.Inputs
import com.jppin.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       binding.btnCalculate.setOnClickListener {
           val weightInput =  binding.TextInputEditPeso.text.toString()
           val heightInput = binding.TextInputEditAltura.text.toString()

            if (validateFields(weightInput, heightInput)) {
                val inputs = Inputs(weightInput, heightInput)
                val openResult = Intent(this, Result::class.java).apply {
                    putExtra("input_data", inputs)
                }
                clearForm()
                startActivity(openResult)
            }
        }
    }
        private fun validateFields(bodyWeightInput: String, heightInput: String): Boolean {
            binding.textInputPesoLayout.error = null
            binding.textInputAlturaLayout.error = null

            if (heightInput.isEmpty()) {
                binding.textInputAlturaLayout.error = getString(R.string.error_altura_empty)
                return false
            }
            if (bodyWeightInput.isEmpty()) {
                binding.textInputPesoLayout.error = getString(R.string.error_peso_empty)
                return false
            }

            return true
        }

        private fun clearForm() {
            binding.TextInputEditAltura.text = null
            binding.TextInputEditPeso.text = null
        }
    }
