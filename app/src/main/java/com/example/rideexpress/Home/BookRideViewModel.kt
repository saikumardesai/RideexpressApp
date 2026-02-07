package com.example.rideexpress.Home



import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch
import retrofit2.Response

class BookRideViewModel(private val repository: RideRepository) : ViewModel() {
    init {
        getRides()
    }
    val rideResponse = MutableLiveData<Response<RideResponse>>()
    val rideList = MutableLiveData<List<RideResponse>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun bookRide(name: String, contact: String, serviceType: String, description: String, createdDate: String) {
        val request = RideRequest(name, contact, serviceType, description, createdDate)
        loading.value = true

        viewModelScope.launch {
            try {
                val response = repository.bookRide(request)
                rideResponse.value = response
            } catch (e: Exception) {
                error.value = e.message
            } finally {
                loading.value = false
            }
        }
    }

    fun getRides() {
        viewModelScope.launch {
            try {
                val rides = repository.getAllRides()
                Log.d("BookRideViewModel", "Fetched rides: $rides")
                rideList.postValue(rides)
            } catch (e: Exception) {
                error.postValue(e.message)
            }
        }
    }


    fun deleteRide(id: String) {
        viewModelScope.launch {
            try {
                repository.deleteRide(id)
                getRides() // refresh list
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }

    fun editRide(ride: RideResponse) {
        viewModelScope.launch {
            try {
                repository.updateRide(ride.id!!, ride)
                getRides()
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }

}
