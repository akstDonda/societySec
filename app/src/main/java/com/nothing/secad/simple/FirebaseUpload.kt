package com.nothing.secad.simple

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.nothing.secad.R
import com.nothing.secad.Society
import com.nothing.secad.databinding.ActivityFirebaseUploadBinding
import java.util.zip.Inflater

class FirebaseUpload : AppCompatActivity() {
    private lateinit var binding: ActivityFirebaseUploadBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var auth = Firebase.auth;

        var society: Society;

        // TODO: take data from intent

//        val society: Society = Society (
//                    name = binding.editTextTextSocietyName.text.toString(),
//                    email = binding.editTextTextEmailAddress.text.toString(),
//                    password =binding.editTextPassword.text.toString(),
//                    uid = auth.currentUser,
//
//             // TODO: take building
//                    isBuilding = true,
//
//                    parking = selectedParking!!.toInt(),
//                    elevators = selectedElevator!!.toInt(),
//                    watchMan = selectedWatchman!!.toInt(),
//                    garden = selectedGarden!!.toInt(),
//                    temple = selectedTemple!!.toInt(),
//                    waterTank = selectedWaterTank!!.toInt(),
//                    totalHouses = selectedTotalHome!!.toInt(),
//
//                    // TODO: calculate price and add
//                    expectedPricePerHouse = 1000

//        )


        // TODO: temp next activity
        val nextIntent = Intent(this, PaymentSendReceiveActivity::class.java)

        // Init database
        val db = Firebase.firestore;
        // Upload data
        db.collection("societies").document(society.uid)
            .set(society)
            // Success
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully written!")
                startActivity(nextIntent)
            }
            // Failure
            .addOnFailureListener {
                e -> Log.w(TAG, "Error writing document", e)
                Toast.makeText(this@FirebaseUpload, e.toString(), Toast.LENGTH_LONG).show()

                val signUpIntent = Intent(this, SignupActivity::class.java)
                startActivity(signUpIntent)
            }

    }
}
