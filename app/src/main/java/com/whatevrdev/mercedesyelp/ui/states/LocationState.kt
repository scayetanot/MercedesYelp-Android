package com.whatevrdev.mercedesyelp.ui.states

import android.location.Location

sealed class LocationState {
    object Empty: LocationState()
    object LocationNeedPermission : LocationState()
    data class LocationOnSuccess(val location: Location) : LocationState()
    data class LocationOnError(val errorMessage: String) : LocationState()
}