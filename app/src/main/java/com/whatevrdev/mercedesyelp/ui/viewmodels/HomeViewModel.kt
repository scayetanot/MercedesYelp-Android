package com.whatevrdev.mercedesyelp.ui.viewmodels

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whatevrdev.domain.entities.AppResult
import com.whatevrdev.domain.usecases.YelpUseCases
import com.whatevrdev.mercedesyelp.ui.states.LocationState
import com.whatevrdev.mercedesyelp.ui.states.SearchResultState
import com.whatevrdev.mercedesyelp.utils.LocationProvider
import com.whatevrdev.mercedesyelp.utils.LocationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationProvider: LocationProvider,
    private val useCases: YelpUseCases
): ViewModel() {


    private val _locationState = MutableStateFlow<LocationState>(LocationState.Empty)
    val locationState: StateFlow<LocationState> get() = _locationState

    private val _searchResultState = MutableStateFlow<SearchResultState>(SearchResultState.Empty)
    val searchResultState: StateFlow<SearchResultState> = _searchResultState

    init {
        getCurrentLocation()
    }

    fun onClick(id: String) {
        _searchResultState.value = SearchResultState.DisplayDetails(id)
    }
    fun requestLocation() = getCurrentLocation()

    fun searchRestaurant(lat: Double, lon: Double) {
        viewModelScope.launch {
            _searchResultState.value = SearchResultState.Loading
            when (val result = useCases.searchForRestaurants(lat, lon)) {
                is AppResult.OnSuccess -> {
                    result.data.let{
                        _searchResultState.value = SearchResultState.Success(it.businesses)
                    }
                }
                is AppResult.OnError -> {
                   _searchResultState.value = SearchResultState.Error(result.exception.message)
                }
            }
        }
    }
    private fun getCurrentLocation() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = locationProvider.getCurrentLocation()) {
                    is LocationResult.LocationSuccess -> {
                        _locationState.value = LocationState.LocationOnSuccess(result.location)
                    }
                    is LocationResult.LocationError ->
                        _locationState.value =
                            LocationState.LocationOnError(result.error.message ?: "Unknown Error")
                    LocationResult.LocationNeedPermission ->
                        _locationState.value = LocationState.LocationNeedPermission
                }
            }
        }
    }
}