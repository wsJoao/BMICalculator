package com.jppin.calculadoraimc.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, BmiFragment(), "BMI")
                .commit()
        }
        binding.switch1.setOnClickListener {
            showFragment("BMI", BmiFragment())
        }
        binding.switch2.setOnClickListener {
            showFragment("IMC", ImcFragment())
        }
    }
    private fun showFragment(tag: String, newFragment: Fragment) {
        val currentFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)

        if (currentFragment?.tag != tag) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, newFragment, tag)
                .commit()
        }
    }
}

