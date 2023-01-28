package com.whatevrdev.mercedesyelp.ui.viewmodels

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whatevrdev.mercedesyelp.utils.LocationProvider
import com.whatevrdev.mercedesyelp.utils.LocationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationProvider: LocationProvider,
): ViewModel() {


    private val _locationState = MutableLiveData<LocationSate>()
    val locationState: LiveData<LocationSate> get() = _locationState

    init {
        getCurrentLocation()
    }

    fun requestLocation() = getCurrentLocation()

    private fun getCurrentLocation() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = locationProvider.getCurrentLocation()) {
                    is LocationResult.LocationSuccess -> {
                        _locationState.postValue(LocationSate.LocationOnSuccess(result.location))
                    }
                    is LocationResult.LocationError ->
                        _locationState.postValue(
                            LocationSate.LocationOnError(result.error.message ?: "Unknown Error")
                        )
                    LocationResult.LocationNeedPermission ->
                        _locationState.postValue(LocationSate.LocationNeedPermission)
                }
            }
        }
    }

    sealed class LocationSate {
        object LocationNeedPermission : LocationSate()
        data class LocationOnSuccess(val location: Location) : LocationSate()
        data class LocationOnError(val errorMessage: String) : LocationSate()
    }
}