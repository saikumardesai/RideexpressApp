package com.example.rideexpress.Home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rideexpress.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val cityUrl = "https://ridexpress.in/localtaxi"
    private val airportUrl = "https://ridexpress.in/airport-taxi"
    private val outstationUrl = "https://ridexpress.in/outstationcabs"
    private val business = "https://ridexpress.in/business-travel-transportation-in-bangalore"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.city.setOnClickListener { openLink(cityUrl) }
        binding.airport.setOnClickListener { openLink(airportUrl) }
        binding.outstation.setOnClickListener { openLink(outstationUrl) }
        binding.localrides.setOnClickListener { openLink(business) }
        binding.btnBookRide.setOnClickListener {
            val intent = Intent(requireContext(), BookRideActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

