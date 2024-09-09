package com.davie.smartparking.ui.admin.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.davie.smartparking.R
import com.davie.smartparking.data.AddSlots
import com.davie.smartparking.databinding.FragmentStatusBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class StatusFragment : Fragment(R.layout.fragment_status) {

    private lateinit var statusBinding: FragmentStatusBinding
    private lateinit var slotsDatabase: DatabaseReference


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusBinding = FragmentStatusBinding.bind(view)

        statusBinding.fabMain.setOnClickListener{
            statusBinding.addSlotsFab.visibility = View.VISIBLE
            statusBinding.txtAdd.visibility = View.VISIBLE
        }


        statusBinding.addSlotsFab.setOnClickListener {

            statusBinding.addSlotsLayout.visibility = View.VISIBLE
            statusBinding.addSlotsFab.visibility = View.GONE
            statusBinding.txtAdd.visibility = View.GONE

            statusBinding.layoutParkedStatus.visibility = View.GONE
            statusBinding.parkedVehicleStatus.visibility = View.GONE

        }

        statusBinding.addSlotsBtn.setOnClickListener {
            addSlots()
        }
        statusBinding.cancelBtn.setOnClickListener {
            statusBinding.addSlotsLayout.visibility = View.GONE
            statusBinding.addSlotsFab.visibility = View.GONE
            statusBinding.txtAdd.visibility = View.GONE

            statusBinding.layoutParkedStatus.visibility = View.VISIBLE
            statusBinding.parkedVehicleStatus.visibility = View.VISIBLE
        }

    }

    private fun addSlots(){
        var parkSlot = statusBinding.edtxtParkSlote.text.toString()
        var parkLocation = statusBinding.edtxtParkLocation.text.toString()
        var parkCapacity = statusBinding.edtxtMaxCapacity.text.toString()

        val slots = com.davie.smartparking.data.AddSlots(parkSlot, parkLocation, parkCapacity)
        slotsDatabase = FirebaseDatabase.getInstance().getReference("Slots")
        slotsDatabase.child(parkSlot).setValue(slots).addOnSuccessListener {

            Toast.makeText(requireContext(), "Slot Added Successfully", Toast.LENGTH_SHORT).show()
            statusBinding.addSlotsLayout.visibility = View.GONE
            statusBinding.addSlotsFab.visibility = View.GONE
            statusBinding.txtAdd.visibility = View.GONE
            statusBinding.fabMain.visibility = View.VISIBLE

            statusBinding.layoutParkedStatus.visibility = View.VISIBLE
            statusBinding.parkedVehicleStatus.visibility = View.VISIBLE
        }
    }
}