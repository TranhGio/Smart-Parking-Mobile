package com.davie.smartparking.ui.admin.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.davie.smartparking.data.ParkingSlots
import com.davie.smartparking.databinding.FragmentControlBinding

class ControlFragment : Fragment() {

    private lateinit var controlBinding: FragmentControlBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controlBinding = FragmentControlBinding.bind(view)

//        var parkSlots = mutableListOf(
//            ParkingSlots("GPO", "Nairobi, CBD", "6"),
//            ParkingSlots("Garden City Mall", "Thika Road", "8"),
//            ParkingSlots("China Square", "Thika Road", "10"),
//            ParkingSlots("Apple Cross Road", "Lavington", "8")
//        )
//
//
//        val itemAdapter = ParkListAdapter(parkSlots)
//        controlBinding.rcvParkingSlots.layoutManager = LinearLayoutManager(requireContext())
//        controlBinding.rcvParkingSlots.adapter = itemAdapter

    }


}