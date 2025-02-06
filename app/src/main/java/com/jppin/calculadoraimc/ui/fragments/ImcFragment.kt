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
import com.jppin.calculadoraimc.databinding.FragmentImcBinding

class ImcFragment : Fragment() {

    private lateinit var viewModel: ImcViewModel
    private lateinit var binding: FragmentImcBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentImcBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ImcViewModel::class.java]
        setListeners()
        return binding.root
    }
    private fun setListeners() {
        binding.btnCalculate.setOnClickListener {
            val weight = binding.etWeight.text.toString()
            val height = binding.etHeight.text.toString()

            val weightError = if (weight.isBlank()) getString(R.string.err_empty_weight) else null
            val heightError = if (height.isBlank()) getString(R.string.err_height_empty) else null

            binding.tilWeight.error = weightError
            binding.tilHeight.error = heightError

            if (weightError == null && heightError == null) {
                viewModel.updateInput(weight, height)
                val inputs = Inputs(
                    viewModel.inputData.value?.weight.toString(),
                    viewModel.inputData.value?.height.toString()
                )
                clearData()
                navigateToResult(this, inputs)
                Toast.makeText(requireActivity(), R.string.toast_done, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun clearData(){
            binding.etWeight.text = null
            binding.etHeight.text = null
    }
}