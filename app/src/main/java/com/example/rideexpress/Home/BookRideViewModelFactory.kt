package com.example.rideexpress.Home



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookRideViewModelFactory(private val repository: RideRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookRideViewModel::class.java)) {
            return BookRideViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
