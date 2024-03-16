package com.nothing.secad.simple

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nothing.secad.R
import com.nothing.secad.databinding.ActivityReceivePaymentUserBinding
import com.nothing.secad.dbHandler.Transaction

class ReceivePaymentUser : AppCompatActivity() {

    private lateinit var binding: ActivityReceivePaymentUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceivePaymentUserBinding.inflate(layoutInflater)
        setContentView(binding.root)






        binding.maintenanceSendNotificationBtn.setOnClickListener(){
            sendNotification()

        }

    }

    fun sendNotification(){
        val db = Firebase.firestore
        val currentUser = FirebaseAuth.getInstance().currentUser!!.uid

        val documentReference = db.collection("societies").document(currentUser)
        documentReference.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // Access the "members" field as an ArrayList
                    val membersList = document.get("members") as ArrayList<*>?

                    Log.d(TAG,  "Members: $membersList")

                    // Check if the field is not null before using it
                    if (membersList != null) {
                        // Print all member names

                        for (member in membersList) {
                            var amountMain = binding.amountMaintenanceEdt.text.toString()
                            var amountMainInt = amountMain.toInt()
                            Transaction("$member",amountMainInt)
                        }
                    } else {
                        println("Members field is null")
                    }
                } else {
                    println("Document not found")
                }
            }
    }

}