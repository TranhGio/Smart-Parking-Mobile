package com.davie.smartparking.ui.driver.dashboard

import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.davie.smartparking.data.api.ApiCall
import com.davie.smartparking.data.api.Operability
import com.davie.smartparking.data.api.SmSUpdate
import com.davie.smartparking.data.api.UpdateStatus
import com.davie.smartparking.databinding.ActivityBookingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingActivity : AppCompatActivity() {

    private lateinit var bookingBinding: ActivityBookingBinding
    private lateinit var operability: com.davie.smartparking.data.api.Operability
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookingBinding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(bookingBinding.root)

        operability = com.davie.smartparking.data.api.Operability()

        bookingBinding.addBookBtn.setOnClickListener {

            bookingBinding.bookStatusLayout.visibility = View.GONE


             val phoneNumber : String= bookingBinding.edtxtPhoneNumber.text.toString()
             val message: String = "Dear User, ticket number 734-A slot has been reserved! Please drive your way"

             operability.SendSMS(phoneNumber, message)

            bookingBinding.bookConfirmedLayout.visibility = View.VISIBLE
           // Toast.makeText(applicationContext, "Message Sent")
            //bookingBinding.bookConfirmedLayout.visibility = View.VISIBLE
        }


        bookingBinding.navigateLocation.setOnClickListener {
            val gmmIntentUri = Uri.parse("google.navigation:q=Apple Cross Roads, Nairobi")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        }
    }

}