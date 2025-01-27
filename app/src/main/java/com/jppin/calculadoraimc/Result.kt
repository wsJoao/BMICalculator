package com.jppin.calculadoraimc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jppin.calculadoraimc.data.Inputs
import com.jppin.calculadoraimc.databinding.ActivityResultBinding

class Result : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val inputs = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("input_data", Inputs::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("input_data")
        }
        inputs?.let {
            getValues(inputs)
            calculateIMC(inputs.bodyWeight.toDouble(), inputs.height.toDouble())
        }
        binding.btnReturn.setOnClickListener {
            finish()
        }
    }

    private fun getValues(inputs: Inputs) {
        val bodyWeight = inputs.bodyWeight
        val height = inputs.height
        val stringInfo =
                    getString(R.string.height_text) + " " +
                    height + " " +
                    getString(R.string.weight_text) + " " +
                    bodyWeight
        binding.textInfo.text = stringInfo
    }
    private fun calculateIMC(bodyWeight: Double, height: Double): Double {
        val imc = bodyWeight / (height * height)
        val diagnosis = when {
            imc < 18.5 -> getString(R.string.diagBaixo)
            imc < 25 -> getString(R.string.diagNormal)
            imc < 30 -> getString(R.string.diagSobrepeso)
            else -> getString(R.string.diagObesidade)
        }
        binding.textDiagnostico.text = diagnosis
        return imc
        }
}