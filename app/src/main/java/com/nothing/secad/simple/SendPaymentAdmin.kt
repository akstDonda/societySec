package com.nothing.secad.simple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nothing.secad.R
import com.nothing.secad.databinding.ActivitySendPaymentAdminBinding

class SendPaymentAdmin : AppCompatActivity() {
    private lateinit var binding: ActivitySendPaymentAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendPaymentAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var button = binding.payButton

        button.setOnClickListener {
            val intent = Intent(this, PayToAdmin::class.java)
            startActivity(intent)
        }
    }
}