package com.whatevrdev.mercedesyelp.ui

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.whatevrdev.mercedesyelp.R
import com.whatevrdev.mercedesyelp.databinding.FragmentHomeBinding
import com.whatevrdev.mercedesyelp.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(
                Manifest.permission.ACCESS_FINE_LOCATION, false) ||
                    permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                viewModel.requestLocation()
            }
            else -> {
                Toast.makeText(
                    requireContext(),
                    "Location Permission Required",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        observeLocation()
    }

    private fun observeLocation() {
        lifecycleScope.launchWhenCreated {
            viewModel.locationState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    HomeViewModel.LocationSate.LocationNeedPermission ->
                        requestLocationPermissions()
                    is HomeViewModel.LocationSate.LocationOnSuccess -> {
                       // Call to get restaurants
                    }
                    is HomeViewModel.LocationSate.LocationOnError ->
                        Toast.makeText(
                            requireContext(),
                            "Error: ${state.errorMessage}",
                            Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun requestLocationPermissions() {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}