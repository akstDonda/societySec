package com.nothing.secad.simple

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.nothing.secad.R
import com.nothing.secad.Transaction
import java.time.LocalDateTime
import java.util.Date

class PayToAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_to_admin)

        createTransaction(100, Intent(this, SendPaymentAdmin::class.java), Intent(this, SendPaymentAdmin::class.java))
    }

    fun createTransaction(amount: Int, successIntent: Intent, failureIntent: Intent) {
        // Create a new transaction
        val transaction = Transaction(Timestamp(Date()), amount)

        // Add the transaction to the database
        val db = Firebase.firestore
        db.collection("transactions")
            .document(Firebase.auth.currentUser?.uid ?: "")
            .update(transaction.date.toString(), transaction)
            // Success
            .addOnSuccessListener {
                Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                startActivity(successIntent)
            }
            // Failure
            .addOnFailureListener {
                    e -> Log.w(ContentValues.TAG, "Error writing document", e)
                Toast.makeText(this@PayToAdmin, e.toString(), Toast.LENGTH_LONG).show()
                Log.e(ContentValues.TAG, e.toString() + ":" + Firebase.auth.currentUser!!.uid)

                val signUpIntent = Intent(this, SignupActivity::class.java)
                startActivity(failureIntent)
            }
    }
}
