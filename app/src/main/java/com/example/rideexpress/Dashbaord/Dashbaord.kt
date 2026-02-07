package com.example.rideexpress.Dashbaord

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rideexpress.About.AboutFragment
import com.example.rideexpress.Home.HomeFragment
import com.example.rideexpress.R
import com.example.rideexpress.Services.ServicesFragment
import com.example.rideexpress.databinding.ActivityDashbaordBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashbaordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashbaordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, HomeFragment())
                        .commit()
                    true
                }

                R.id.nav_services -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, ServicesFragment())
                        .commit()
                    true
                }

                R.id.nav_about -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, AboutFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }
}
