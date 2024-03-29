package com.nothing.secad.simple

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nothing.secad.R
import com.nothing.secad.UserTransactionShowActivity
import com.nothing.secad.databinding.ActivityReceivePaymentUserBinding
import com.nothing.secad.dbHandler.Transaction
import com.nothing.secad.model.userTransaction

class ReceivePaymentUser : AppCompatActivity() {

    private lateinit var binding: ActivityReceivePaymentUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceivePaymentUserBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.userTransactionShowBtn.setOnClickListener(){
            var intent = Intent(this, UserTransactionShowActivity::class.java)
            startActivity(intent)

        }


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
                    val membersList = document.get("memberIDs") as ArrayList<*>?

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