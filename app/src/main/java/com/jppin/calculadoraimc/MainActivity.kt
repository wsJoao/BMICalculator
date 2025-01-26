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

    private lateinit var textInputHeight : TextInputLayout
    private lateinit var textInputWeight: TextInputLayout

    private lateinit var editHeight: TextInputEditText
    private lateinit var editWeight: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()

        buttonCalculate.setOnClickListener {
            val bodyWeightInput = editWeight.text.toString()
            val heightInput = editHeight.text.toString()

            val inputs = Inputs(bodyWeightInput, heightInput)
            val validateFields = validateFields(bodyWeightInput, heightInput)

            val openResult = Intent(this, Result::class.java)
            if (validateFields){
                openResult.putExtra("input_data",inputs)
                clearForm()
                startActivity(openResult)
            }
        }
    }

        private fun validateFields(bodyWeightInput: String, heightInput: String): Boolean {
            textInputWeight.error = null
            textInputHeight.error = null

            if (heightInput.isEmpty() && bodyWeightInput.isEmpty()) {
                textInputHeight.error = getString(R.string.error_altura_empty)
                textInputWeight.error = getString(R.string.error_peso_empty)
                return false
            }
            if (heightInput.isEmpty()) {
                textInputHeight.error = getString(R.string.error_altura_empty)
                return false
            }
            if (bodyWeightInput.isEmpty()) {
                textInputWeight.error = getString(R.string.error_peso_empty)
                return false
            }

            return true
        }


        private fun initComponents() {
            buttonCalculate = findViewById(R.id.btn_Calculate)

            textInputWeight = findViewById(R.id.textInputPesoLayout)
            textInputHeight = findViewById(R.id.textInputAlturaLayout)

            editWeight = findViewById(R.id.TextInputEditPeso)
            editHeight = findViewById(R.id.TextInputEditAltura)

        }

        private fun clearForm() {
            editWeight.text = null
            editHeight.text = null
        }
    }