package com.davie.smartparking.ui.admin.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.davie.smartparking.data.AddSlots
import com.davie.smartparking.data.api.Operability
import com.davie.smartparking.data.mqtt.MattViewModel
import com.davie.smartparking.databinding.ActivityTestBinding
import com.davie.smartparking.ui.driver.dashboard.BookingActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TestActivity : AppCompatActivity() {
    private lateinit var testBinding: ActivityTestBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var parkingArrayList: ArrayList<com.davie.smartparking.data.AddSlots>

    private lateinit var mqttViewModel: MattViewModel

    private lateinit var operability: com.davie.smartparking.data.api.Operability
    override fun onCreate(savedInstanceState: Bundle?) {
        testBinding = ActivityTestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(testBinding.root)

        //connecting to the server option
         mqttViewModel = ViewModelProvider(this).get(MattViewModel::class.java)
         mqttViewModel.connect(this)

        operability = com.davie.smartparking.data.api.Operability()

        testBinding.rcvParkingSlots.layoutManager = LinearLayoutManager(this)
        testBinding.rcvParkingSlots.setHasFixedSize(true)
        parkingArrayList = arrayListOf<com.davie.smartparking.data.AddSlots>()
        getData();


    }

    private fun getData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Slots")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        var slots = userSnapshot.getValue(com.davie.smartparking.data.AddSlots::class.java)

                        parkingArrayList.add(slots!!)
                    }




                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}