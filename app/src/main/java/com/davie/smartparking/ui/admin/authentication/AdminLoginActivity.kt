package com.davie.smartparking.ui.admin.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.davie.smartparking.R
import com.davie.smartparking.databinding.ActivityAdminloginBinding
import com.davie.smartparking.databinding.ActivityDriverLoginBinding
import com.davie.smartparking.ui.onboarding.IntroActivity

class AdminLoginActivity : AppCompatActivity() {
    private lateinit var adminLoginBinding: ActivityAdminloginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adminLoginBinding = ActivityAdminloginBinding.inflate(layoutInflater)
        setContentView(adminLoginBinding.root)

        adminLoginBinding.backIcon.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}