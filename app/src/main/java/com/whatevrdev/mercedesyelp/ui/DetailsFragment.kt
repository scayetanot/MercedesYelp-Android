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
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.whatevrdev.mercedesyelp.R
import com.whatevrdev.mercedesyelp.databinding.FragmentDetailsBinding
import com.whatevrdev.mercedesyelp.ui.adapters.RestaurantDetailsAdapter
import com.whatevrdev.mercedesyelp.ui.adapters.RestaurantReviewsAdapter
import com.whatevrdev.mercedesyelp.ui.states.RestaurantDetailsState
import com.whatevrdev.mercedesyelp.ui.states.RestaurantReviewsState
import com.whatevrdev.mercedesyelp.ui.viewmodels.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val viewModel: DetailsViewModel by viewModels()
    private var restaurantId: String = ""

    private val restaurantDetailsAdapter: RestaurantDetailsAdapter by lazy {
        RestaurantDetailsAdapter(context, viewModel)
    }

    private val restaurantReviewsAdapter: RestaurantReviewsAdapter by lazy {
        RestaurantReviewsAdapter(context)
    }

    private val concatAdapter: ConcatAdapter by lazy {
        ConcatAdapter(
            restaurantDetailsAdapter,
            restaurantReviewsAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        restaurantId = arguments?.getString("id").toString()

        if(restaurantId.isNullOrBlank()) {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_restaurant_id),
                Toast.LENGTH_LONG).show()
            findNavController().navigateUp()
        } else {
            viewModel.getRestaurantInformation(restaurantId)
        }
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        binding.detailsConcatView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = concatAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToRestaurantDetails()
        subscribeToRestaurantReviews()
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

    private fun subscribeToRestaurantReviews() {
        lifecycleScope.launchWhenCreated {
            viewModel.restaurantReviewsState.collect {
                when(it) {
                    is RestaurantReviewsState.Success -> {
                        restaurantReviewsAdapter.submitList(it.restaurantReviews)
                        binding.loading.loadingLayout.isVisible = false
                    }
                    is RestaurantReviewsState.Error -> {
                        binding.loading.loadingLayout.isVisible = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    is RestaurantReviewsState.Loading -> {
                        binding.loading.loadingLayout.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }

}