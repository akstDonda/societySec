package com.nothing.secad.simple

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.nothing.secad.databinding.ActivitySignUpBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth;

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
                createUser(binding.editTextTextEmailAddress.toString(), binding.editTextPassword.toString())
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

    private fun createUser(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed." + task.exception,
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
}
