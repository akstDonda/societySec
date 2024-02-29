package com.nothing.secad.simple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nothing.secad.databinding.ActivityBuildingFormBinding

class BuildingForm : AppCompatActivity() {

    private lateinit var binding : ActivityBuildingFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuildingFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBuildingSignUp.setOnClickListener(){

            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)

        }

    }
}