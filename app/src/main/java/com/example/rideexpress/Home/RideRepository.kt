package com.example.rideexpress.Home
import com.example.rideexpress.common.RetrofitInstance
import retrofit2.Response

class RideRepository {

    suspend fun bookRide(rideRequest: RideRequest): Response<RideResponse> {
        return RetrofitInstance.api.bookRide(rideRequest)
    }


    // Fetch all booked rides
    suspend fun getAllRides(): List<RideResponse> {
        return RetrofitInstance.api.getRides()
    }

    // Update ride
    suspend fun updateRide(id: String, rideRequest: RideResponse): Response<RideResponse> {
        return RetrofitInstance.api.updateRide(id, rideRequest)
    }

    // Delete ride
    suspend fun deleteRide(id: String): Response<Void> {
        return RetrofitInstance.api.deleteRide(id)
    }
}
