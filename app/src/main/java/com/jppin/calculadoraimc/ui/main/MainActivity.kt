package com.jppin.calculadoraimc.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jppin.calculadoraimc.R
import com.jppin.calculadoraimc.databinding.ActivityMainBinding
import com.jppin.calculadoraimc.ui.fragments.BmiFragment
import com.jppin.calculadoraimc.ui.fragments.ImcFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        changeFragment(ImcFragment())
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                changeFragment(BmiFragment())
            } else {
                changeFragment(ImcFragment())
            }
        }
    }
    private fun changeFragment(fragment: androidx.fragment.app.Fragment) {
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainer) != fragment) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}

