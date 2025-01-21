package com.jppin.calculadoraimc

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jppin.calculadoraimc.data.Inputs

class Result : AppCompatActivity() {

    private lateinit var buttonReturn: Button
    private lateinit var textInfo: TextView
    private lateinit var textDiagnostico: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        initComponents()
        val inputs = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("input_data", Inputs::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("input_data")
        }
        inputs?.let {
            getValues(inputs)
            calculateIMC(inputs.peso.toDouble(), inputs.altura.toDouble())
        }
        buttonReturn.setOnClickListener {
            finish()
        }
    }

    private fun getValues(inputs: Inputs) {
        val peso = inputs.peso
        val altura = inputs.altura
        val stringInfo = "Altura: $altura/ Peso: $peso"
        textInfo.text = stringInfo
    }
    private fun calculateIMC(peso: Double, altura: Double): Double {
        val imc = peso / (altura * altura)
        val diagnostico = when {
            imc < 18.5 -> getString(R.string.diagBaixo)
            imc < 25 -> getString(R.string.diagNormal)
            imc < 30 -> getString(R.string.diagSobrepeso)
            else -> getString(R.string.diagObesidade)
        }
        textDiagnostico.text = diagnostico
        return imc
        }

    private fun initComponents(){
        buttonReturn = findViewById(R.id.btnReturn)
        textInfo = findViewById(R.id.textInfo)
        textDiagnostico = findViewById(R.id.textDiagnostico)

    }
}