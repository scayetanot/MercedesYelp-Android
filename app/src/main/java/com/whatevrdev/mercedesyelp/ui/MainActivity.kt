package com.whatevrdev.mercedesyelp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.whatevrdev.mercedesyelp.R
import com.whatevrdev.mercedesyelp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}