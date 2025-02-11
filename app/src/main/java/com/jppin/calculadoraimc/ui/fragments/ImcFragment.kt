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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImcFragment : Fragment() {

    private lateinit var binding: FragmentImcBinding
    private lateinit var viewModel: ImcViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentImcBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ImcViewModel::class.java]
        setObserver()
        setListeners()
        return binding.root
    }

    private fun setObserver() {
        viewModel.errorData.observe(viewLifecycleOwner) { (weightError, heightError) ->
            binding.tilWeight.error = weightError
            binding.tilHeight.error = heightError
        }
    }

    private fun setListeners() {
        binding.btnCalculate.setOnClickListener {
            val (weight, height) = Pair(binding.etWeight.text.toString(), binding.etHeight.text.toString())
            viewModel.checkInputs(weight, height)

            if (viewModel.errorData.value?.let{it.first == null && it.second == null} == true) {
                viewModel.updateInput(weight, height)
                val inputs = Inputs(weight, height)
                navigateToResult(this, inputs)
                showToast()
                clearData()
                }
            }
        }

    private fun showToast(){
        Toast.makeText(requireActivity(), R.string.toast_done, Toast.LENGTH_SHORT).show()
    }

    private fun clearData(){
            binding.etWeight.text = null
            binding.etHeight.text = null
    }
}