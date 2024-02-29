package com.nothing.secad.simple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nothing.secad.databinding.ActivityRowHouseFormBinding



class RowHouseForm : AppCompatActivity() {

    private lateinit var binding: ActivityRowHouseFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRowHouseFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRowHouseSignUp.setOnClickListener(){
            var intent = Intent(this@RowHouseForm, SignupActivity::class.java);
            startActivity(intent)
        }


    }
}
