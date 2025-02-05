package com.jppin.calculadoraimc.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jppin.calculadoraimc.R
import com.jppin.calculadoraimc.data.Inputs
import com.jppin.calculadoraimc.data.NavigationHelper.navigateToResult
import com.jppin.calculadoraimc.databinding.FragmentBmiBinding

class BmiFragment : Fragment() {

    private lateinit var binding: FragmentBmiBinding
    private lateinit var viewModel: BmiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBmiBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[BmiViewModel::class.java]
        setObservers()
        setListeners()
        Log.d("BmiFragment", "onCreateView called")
        return binding.root
    }
    private fun setObservers(){
         viewModel.bmiData.observe(viewLifecycleOwner) { inputs ->
             Log.d("BmiFragment", "bmiData updated: $inputs")
                 binding.etPounds.setText(inputs.pounds)
                 binding.etFeet.setText(inputs.feet)
                 binding.etInches.setText(inputs.inches)
         }
    }
    private fun setListeners() {
        binding.btnCalculate.setOnClickListener {
            val feet = binding.etFeet.text.toString()
            val inches = binding.etInches.text.toString()
            val pounds = binding.etPounds.text.toString()

            val feetError = if (feet.isBlank()) getString(R.string.err_empty_feet) else null
            val inchesError = if (inches.isBlank()) getString(R.string.err_empty_inches) else null
            val poundsError = if (pounds.isBlank()) getString(R.string.err_empty_pounds) else null

            binding.tilFeet.error = feetError
            binding.tilInches.error = inchesError
            binding.tilPounds.error = poundsError

            if (feetError == null && inchesError == null && poundsError == null) {
                viewModel.updateInput(feet, inches, pounds)
                viewModel.bmiConversion(feet, inches, pounds)

                val inputs = Inputs(viewModel.inputData.value?.weight.toString(), viewModel.inputData.value?.height.toString())
                navigateToResult(this,inputs)
                Toast.makeText(requireContext(), R.string.toast_done, Toast.LENGTH_SHORT).show()
                viewModel.clearData()
            }
        }
    }
}