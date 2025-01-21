package com.jppin.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jppin.calculadoraimc.data.Inputs

class MainActivity : AppCompatActivity() {


    private lateinit var buttonCalculate: Button

    private lateinit var textInputAltura : TextInputLayout
    private lateinit var textInputPeso: TextInputLayout

    private lateinit var editAltura: TextInputEditText
    private lateinit var editPeso: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()

        buttonCalculate.setOnClickListener {
            val pesoInput = editPeso.text.toString()
            val alturaInput = editAltura.text.toString()

            val inputs = Inputs(pesoInput, alturaInput)
            val validateFields = validateFields(pesoInput, alturaInput)

            val openResult = Intent(this, Result::class.java)
            if (validateFields){
                openResult.putExtra("input_data",inputs)
                clearForm()
                startActivity(openResult)
            }
        }
    }

        private fun validateFields(pesoInput: String, alturaInput: String): Boolean {
            textInputPeso.error = null
            textInputAltura.error = null

            if (alturaInput.isEmpty() && pesoInput.isEmpty()) {
                textInputAltura.error = getString(R.string.error_altura_empty)
                textInputPeso.error = getString(R.string.error_peso_empty)
                return false
            }
            if (alturaInput.isEmpty()) {
                textInputAltura.error = getString(R.string.error_altura_empty)
                return false
            }
            if (pesoInput.isEmpty()) {
                textInputPeso.error = getString(R.string.error_peso_empty)
                return false
            }

            return true
        }


        private fun initComponents() {
            buttonCalculate = findViewById(R.id.btn_Calculate)

            textInputPeso = findViewById(R.id.textInputPesoLayout)
            textInputAltura = findViewById(R.id.textInputAlturaLayout)

            editPeso = findViewById(R.id.TextInputEditPeso)
            editAltura = findViewById(R.id.TextInputEditAltura)

        }

        private fun clearForm() {
            editPeso.text = null
            editAltura.text = null
        }
    }