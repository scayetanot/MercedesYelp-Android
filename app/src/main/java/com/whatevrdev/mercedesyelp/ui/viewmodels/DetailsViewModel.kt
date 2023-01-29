package com.whatevrdev.mercedesyelp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whatevrdev.domain.entities.AppResult
import com.whatevrdev.domain.usecases.YelpUseCases
import com.whatevrdev.mercedesyelp.ui.states.RestaurantDetailsState
import com.whatevrdev.mercedesyelp.ui.states.SearchResultState
import com.whatevrdev.mercedesyelp.utils.LocationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: YelpUseCases
): ViewModel() {

    private val _restaurantDetailsState = MutableStateFlow<RestaurantDetailsState>(RestaurantDetailsState.Empty)
    val restaurantDetailsState: StateFlow<RestaurantDetailsState> = _restaurantDetailsState

    fun getRestaurantDetails(id: String) {
        viewModelScope.launch {
            _restaurantDetailsState.value = RestaurantDetailsState.Loading
            when (val result = useCases.getRestaurantDetails(id)) {
                is AppResult.OnSuccess -> {
                    result.data.let {
                        _restaurantDetailsState.value = RestaurantDetailsState.Success(it)
                    }
                }
                is AppResult.OnError -> {
                 _restaurantDetailsState.value = RestaurantDetailsState.Error(result.exception.message)
                }
            }
        }
    }
}