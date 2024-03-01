package com.nothing.secad.simple

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nothing.secad.databinding.ActivitySignUpBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //come with intent data to form
        val selectedParking = intent.getStringExtra("selectedParking")
        val selectedElevator = intent.getStringExtra("selectedElevator")
        val selectedWatchman = intent.getStringExtra("selectedWatchman")
        val selectedGarden = intent.getStringExtra("selectedGarden")
        val selectedTemple = intent.getStringExtra("selectedTemple")
        val selectedWaterTank = intent.getStringExtra("selectedWaterTank")
        val selectedTotalHome = intent.getStringExtra("selectedTotalHome")

        // Initialize your views
        val registerButton = binding.btnRegisterWelcome

        // Set click listener for the registerButton
        registerButton.setOnClickListener {
            clearErrors() // Clear previous errors

            if (validateInput()) {
                Toast.makeText(this,"sucess",Toast.LENGTH_LONG).show()
                var intent = Intent(this,PaymentSendReceiveActivity::class.java)
                startActivity(intent);
            }
        }
    }

    // Function to validate input fields
    private fun validateInput(): Boolean {
        var isValid = true

        val userName = binding.editTextTextSocietyName.text.toString().trim()
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val confirmPassword = binding.editTextConformPassword.text.toString().trim()

        // Add your validation logic here
        if (userName.isEmpty()) {
            binding.editTextTextSocietyName.setError("Username is required")
            isValid = false
        }

        if (email.isEmpty() || !isValidEmailFormat(email)) {
            binding.editTextTextEmailAddress.setError("Invalid email format")
            isValid = false
        }

        if (password.isEmpty()) {
            binding.editTextPassword.setError("Password is required")
            isValid = false
        }

        if (confirmPassword.isEmpty()) {
            binding.editTextConformPassword.setError("Confirm Password is required")
            isValid = false
        }

        if (password != confirmPassword) {
            binding.editTextConformPassword.setError("Passwords do not match")
            isValid = false
        }

        // Add more validation as needed

        return isValid
    }
    private fun isValidEmailFormat(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$"
        return email.matches(emailRegex.toRegex())
    }

    // Function to clear errors on all EditText widgets
    private fun clearErrors() {
        binding.editTextTextSocietyName.error = null
        binding.editTextTextEmailAddress.error = null
        binding.editTextPassword.error = null
        binding.editTextConformPassword.error = null
    }
}
