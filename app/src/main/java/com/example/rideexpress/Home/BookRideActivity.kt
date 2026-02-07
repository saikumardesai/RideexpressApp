package com.example.rideexpress.Home

import com.example.rideexpress.R
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.rideexpress.databinding.ActivityBookRideBinding


class BookRideActivity : AppCompatActivity() {
    private lateinit var rideAdapter: RideAdapter
    private lateinit var binding: ActivityBookRideBinding
    private val viewModel: BookRideViewModel by viewModels {
        BookRideViewModelFactory(RideRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookRideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val name = binding.etName.text.toString()
            val contact = binding.etContact.text.toString()
            val serviceType = binding.spinnerServiceType.selectedItem.toString()
            val description = binding.etDescription.text.toString()
            val createdDate = binding.etCreatedDate.text.toString()

            if (name.isEmpty() || contact.isEmpty() || description.isEmpty() || createdDate.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.bookRide(name, contact, serviceType, description, createdDate)
            }
        }


        setupRecyclerView()
        observeViewModel()
        setupSpinner()
    }

    private fun setupSpinner() {
        val serviceTypes = listOf("City Taxi", "Airport Taxi", "Outstation", "Business Transport")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, serviceTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerServiceType.adapter = adapter
    }


    private fun observeViewModel() {
        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
        }

        viewModel.rideResponse.observe(this) { response ->
            if (response.isSuccessful) {
                Toast.makeText(this, "Ride booked successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed: ${response.message()}", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(this) { err ->
            Toast.makeText(this, "Error: $err", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        rideAdapter = RideAdapter(mutableListOf(), object : RideAdapter.RideActionListener {
            override fun onEdit(ride: RideResponse) {
                binding.etName.setText(ride.name)
                binding.etContact.setText(ride.phone)
                binding.etDescription.setText(ride.notes)
                binding.etCreatedDate.setText(ride.createdAt)
                val pos = (binding.spinnerServiceType.adapter as ArrayAdapter<String>).getPosition(ride.serviceType)
                binding.spinnerServiceType.setSelection(pos)


                binding.btnSubmit.setOnClickListener {
                    val updatedRide = RideResponse(
                        id = ride.id,
                        name = binding.etName.text.toString(),
                        phone = binding.etContact.text.toString(),
                        serviceType = binding.spinnerServiceType.selectedItem.toString(),
                        notes = binding.etDescription.text.toString(),
                        createdAt = binding.etCreatedDate.text.toString()
                    )
                    viewModel.editRide(updatedRide)
                    clearForm()
                }
            }

            override fun onDelete(ride: RideResponse) {
                viewModel.deleteRide(ride.id)
            }
        })

        binding.rvRides.layoutManager = LinearLayoutManager(this)
        binding.rvRides.adapter = rideAdapter


        viewModel.rideList.observe(this) {
            Log.d("BookRideActivity", "Rides fetched: ${it.size}")
            rideAdapter.updateList(it)
        }
    }


    private fun clearForm() {
        binding.etName.text?.clear()
        binding.etContact.text?.clear()
        binding.etDescription.text?.clear()
        binding.etCreatedDate.text?.clear()
        binding.spinnerServiceType.setSelection(0)
    }
}

