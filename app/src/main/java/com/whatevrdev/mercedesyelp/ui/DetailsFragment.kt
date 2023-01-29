package com.whatevrdev.mercedesyelp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.whatevrdev.mercedesyelp.R
import com.whatevrdev.mercedesyelp.databinding.FragmentDetailsBinding
import com.whatevrdev.mercedesyelp.ui.states.RestaurantDetailsState
import com.whatevrdev.mercedesyelp.ui.viewmodels.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val viewModel: DetailsViewModel by viewModels()
    private var restaurantId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        restaurantId = arguments?.getString("id").toString()

        if(restaurantId.isNullOrBlank()) {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_restaurant_id),
                Toast.LENGTH_LONG).show()
            findNavController().navigateUp()
        } else {
            viewModel.getRestaurantDetails(restaurantId)
        }
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToRestaurantDetails()
    }

    private fun subscribeToRestaurantDetails() {
        lifecycleScope.launchWhenCreated {
            viewModel.restaurantDetailsState.collect {
                when(it) {
                    is RestaurantDetailsState.Success -> {
                        binding.loading.loadingLayout.isVisible = false
                    }
                    is RestaurantDetailsState.Error -> {
                        binding.loading.loadingLayout.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    is RestaurantDetailsState.Loading -> {
                        binding.loading.loadingLayout.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }

}