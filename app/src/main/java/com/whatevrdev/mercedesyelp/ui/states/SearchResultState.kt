package com.whatevrdev.mercedesyelp.ui.states

import com.whatevrdev.domain.entities.YelpBusiness

sealed class SearchResultState {
    object Empty: SearchResultState()
    object Loading: SearchResultState()
    data class Error(val message: String?) : SearchResultState()
    data class Success(val listOfRestaurants: List<YelpBusiness>) : SearchResultState()
    data class DisplayDetails(val id: String) : SearchResultState()
}