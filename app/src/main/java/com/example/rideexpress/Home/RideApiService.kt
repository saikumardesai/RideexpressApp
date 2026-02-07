package com.example.rideexpress.Home

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RideApiService {

    // CREATE - Book a new ride
    @POST("api/requests")
    suspend fun bookRide(@Body rideRequest: RideRequest): Response<RideResponse>

    // READ - Get all booked rides
    @GET("api/requests")
    suspend fun getRides(): List<RideResponse>

    // UPDATE - Update a booked ride
    @PUT("api/requests/{id}")
    suspend fun updateRide(
        @Path("id") id: String,
        @Body rideRequest: RideResponse
    ): Response<RideResponse>

    // DELETE - Delete a ride
    @DELETE("api/requests/{id}")
    suspend fun deleteRide(@Path("id") id: String): Response<Void>
}
