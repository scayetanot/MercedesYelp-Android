package com.whatevrdev.mercedesyelp.ui

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.whatevrdev.mercedesyelp.databinding.FragmentHomeBinding
import com.whatevrdev.mercedesyelp.ui.adapters.ListOfRestaurantsAdapter
import com.whatevrdev.mercedesyelp.ui.states.LocationState
import com.whatevrdev.mercedesyelp.ui.states.SearchResultState
import com.whatevrdev.mercedesyelp.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private val listOfRestaurantsAdapter : ListOfRestaurantsAdapter by lazy {
        ListOfRestaurantsAdapter(viewModel)
    }

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
        binding.homeListOfRestaurantsResults.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listOfRestaurantsAdapter
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        subscribeToLocation()
        subscribeToSearchResult()
    }

    private fun requestLocationPermissions() {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun subscribeToLocation() {
        lifecycleScope.launchWhenCreated {
            viewModel.locationState.collect {
                when (it) {
                    LocationState.LocationNeedPermission -> {
                        requestLocationPermissions()
                    }
                    is LocationState.LocationOnSuccess -> {
                        viewModel.searchRestaurant(it.location.latitude, it.location.longitude)
                    }
                    is LocationState.LocationOnError -> {
                        Toast.makeText(
                            requireContext(),
                            "Error: ${it.errorMessage}",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
    private fun subscribeToSearchResult() {
        lifecycleScope.launchWhenCreated {
            viewModel.searchResultState.collect {
                when(it) {
                    SearchResultState.Loading ->
                        binding.loading.loadingLayout.isVisible = true
                    is SearchResultState.Success -> {
                        binding.loading.loadingLayout.isVisible = false
                        listOfRestaurantsAdapter.submitList(it.listOfRestaurants)
                    }
                    is SearchResultState.Error -> {
                        binding.loading.loadingLayout.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}