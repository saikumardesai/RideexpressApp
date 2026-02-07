package com.example.rideexpress.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rideexpress.databinding.ItemRideBinding

class RideAdapter(
    private var rides: MutableList<RideResponse>,
    private val listener: RideActionListener
) : RecyclerView.Adapter<RideAdapter.RideViewHolder>() {

    interface RideActionListener {
        fun onEdit(ride: RideResponse)
        fun onDelete(ride: RideResponse)
    }

    inner class RideViewHolder(val binding: ItemRideBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ride: RideResponse) {
            binding.tvName.text = ride.name
            binding.tvContact.text = ride.phone
            binding.tvService.text = ride.serviceType
            binding.tvNotes.text = ride.notes
            binding.tvDate.text = ride.createdAt

            binding.btnEdit.setOnClickListener { listener.onEdit(ride) }
            binding.btnDelete.setOnClickListener { listener.onDelete(ride) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val binding = ItemRideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RideViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        holder.bind(rides[position])
    }

    override fun getItemCount(): Int = rides.size

    fun updateList(newList: List<RideResponse>) {
        rides.clear()
        rides.addAll(newList)
        notifyDataSetChanged()
    }

}
