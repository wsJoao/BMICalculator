package com.jppin.calculadoraimc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jppin.calculadoraimc.data.IntentProcessor
import com.jppin.calculadoraimc.data.ResultViewModel
import com.jppin.calculadoraimc.databinding.ActivityResultBinding

class Result : AppCompatActivity() {

    private val viewModel = ResultViewModel()
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setObservers()
        setListeners()
        processInputs(intent)
    }
    private fun setObservers() {
        viewModel.resultData.observe(this) { resultData ->
            binding.textDiagnosis.text = resultData.diagnosis
            binding.textInfo.text = resultData.imc
        }
    }
    private fun setListeners() {
        binding.btnReturn.setOnClickListener {
            finish()
        }
    }
    private fun processInputs(intent: Intent) {
        val inputs = IntentProcessor.extractInputs(intent)
        inputs?.let {
            viewModel.imcData(inputs.weight.toDouble(), inputs.height.toDouble(), this)
        }
    }
}
