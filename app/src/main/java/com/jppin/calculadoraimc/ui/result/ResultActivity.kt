package com.jppin.calculadoraimc.ui.result

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jppin.calculadoraimc.R
import com.jppin.calculadoraimc.data.IntentProcessor
import com.jppin.calculadoraimc.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var viewModel : ResultViewModel
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[ResultViewModel::class.java]
        setObservers()
        setListeners()
        processInputs(intent)
    }

    private fun setObservers() {
        viewModel.resultsData.observe(this) { resultData ->
            binding.textDiagnosis.text = resultData.diagnosis
            binding.textInfo.text = resultData.imc
        }
    }

    private fun setListeners() {
        binding.btnReturn.setOnClickListener {
            Toast.makeText(this, R.string.toast_return, Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    private fun processInputs(intent: Intent) {
        val inputs = IntentProcessor.extractInputs(intent)
        val diagnosis = Diagnosis(
            underweight = getString(R.string.diagUndw),
            normal = getString(R.string.diagNormal),
            overweight = getString(R.string.diagOvw),
            obesity = getString(R.string.diagObese)
        )
        inputs?.let {
            viewModel.imcData(inputs.weight.toDouble(), inputs.height.toDouble(), diagnosis)
        }
    }
}
