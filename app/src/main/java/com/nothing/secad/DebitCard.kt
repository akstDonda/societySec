package com.nothing.secad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nothing.secad.databinding.ActivityDebitCardBinding

class DebitCard : AppCompatActivity() {

    private lateinit var binding: ActivityDebitCardBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDebitCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addAmount = intent.getStringExtra("amount")
        val intAddAmount = addAmount?.toIntOrNull() ?: 0

        val btn: Button = findViewById(R.id.add_money_wallet_debitCard)
        btn.setOnClickListener(){
            //TODO:increase
            updateCurrentAmount(intAddAmount)
        }

    }



    private fun updateCurrentAmount(addAmount: Int) {
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            val uid = user.uid
            val memberDocRef = firestore.collection("societies").document(uid)

            // Get the current value of "currentAmount" from Firestore
            memberDocRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val currentAmount = documentSnapshot.getLong("currentAmount")?.toInt() ?: 0
                        val newAmount = currentAmount + addAmount

                        // Update the "currentAmount" field with the new amount
                        memberDocRef.update("currentAmount", newAmount)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Amount Added", Toast.LENGTH_SHORT).show()
                                var intent = Intent(this, addWallet_animation::class.java)
                                startActivity(intent)
                                finish() // Close the activity if the update is successful
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Error Adding Amount", Toast.LENGTH_SHORT).show()
                                e.printStackTrace()
                                finish() // Close the activity if the update fails
                            }
                    } else {
                        Toast.makeText(this, "Document does not exist", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error retrieving current amount", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
        }
    }

}