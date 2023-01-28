package com.whatevrdev.mercedesyelp.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationProvider @Inject constructor(
    private val appContext: Context
) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): LocationResult = suspendCoroutine { continuation ->

        val defaultLocation = Location("").apply {
            latitude = 33.83422265228964
            longitude = -118.21241904417631
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(appContext)

        if (checkPermissions()) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    continuation.resume(
                        LocationResult.LocationSuccess(location ?: defaultLocation)
                    )

                }
                .addOnFailureListener {
                    continuation.resume(LocationResult.LocationError(it))
                }
        } else {
            continuation.resume(LocationResult.LocationNeedPermission)
        }
    }

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            appContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            appContext,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}

sealed class LocationResult {
    object LocationNeedPermission : LocationResult()
    data class LocationSuccess(val location: Location) : LocationResult()
    data class LocationError(val error: Throwable) : LocationResult()
}