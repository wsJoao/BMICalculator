package com.jppin.calculadoraimc.ui.fragments

import android.os.Bundle
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BmiFragment : Fragment() {

    private lateinit var viewModel: BmiViewModel
    private lateinit var binding: FragmentBmiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentBmiBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[BmiViewModel::class.java]
        setObserver()
        setListeners()
        return binding.root
    }

    private fun setObserver() {
        viewModel.errors.observe(viewLifecycleOwner) { (feetError, inchesError, poundsError) ->
            binding.tilFeet.error = feetError
            binding.tilInches.error = inchesError
            binding.tilPounds.error = poundsError
        }
    }

    private fun setListeners() {
            binding.btnCalculate.setOnClickListener {
                val (feet, inches, pounds) = Triple(
                    binding.etFeet.text.toString(),
                    binding.etInches.text.toString(),
                    binding.etPounds.text.toString())

                viewModel.checkFields(feet, inches, pounds)

                if (viewModel.errors.value?.let { it.first == null && it.second == null && it.third == null } == true) {
                    viewModel.updateInput(feet, inches, pounds)
                    viewModel.bmiConversion(feet, inches, pounds)

                    val inputs = Inputs(
                        viewModel.inputData.value?.weight.toString(),
                        viewModel.inputData.value?.height.toString()
                    )
                    navigateToResult(this, inputs)
                    showToast()
                    clearData()
                } else {
                    Toast.makeText(requireContext(), "Falha", Toast.LENGTH_SHORT).show()
                }
            }
        }

    private fun showToast() {
        Toast.makeText(requireContext(), R.string.toast_done, Toast.LENGTH_SHORT).show()
    }

    private fun clearData() {
        binding.etFeet.text = null
        binding.etInches.text = null
        binding.etPounds.text = null
    }
}