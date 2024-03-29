package com.nothing.secad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nothing.secad.databinding.ActivityBuildingProfileBinding
import com.nothing.secad.databinding.NoteLayoutBinding

class Building_profile : AppCompatActivity() {

    private lateinit var binding: ActivityBuildingProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuildingProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        UserDataFetch()


    }
    fun UserDataFetch() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val db = FirebaseFirestore.getInstance()
        if (uid != null) {
            db.collection("societies").document(uid).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        // Access the fields you need
                        val societyname = document.getString("name")!!
                        val socEmail = document.getString("email")
                        val totalHouse = document.getLong("totalHouses")?.toString() // Retrieve as Long and then convert to String
                        val maintenanceTotal = "0"
//                        val socImage = document.getString("userImage")
                        val perHomeAmount = document.getLong("expectedPricePerHouse")?.toString()


                        // Update the UI on the main thread
                        runOnUiThread {
                            if ( societyname != null && socEmail != null && totalHouse != null && maintenanceTotal != null && totalHouse != null && perHomeAmount != null) {
                                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
//

                                binding.profileSocNameTxt.text =   "society Name: ${societyname}"
                                binding.profileEmailTxt.text = "society Email: ${socEmail}"
                                binding.profileHouseNoTxt.text =  "Total House: ${totalHouse}"
                                binding.totalMaintenance.text = "Maintenance Total: ${totalHouse.toInt()*perHomeAmount.toInt()}"
                                binding.perHomePayAbleAmount.text =  "Home Payable Amount: ${perHomeAmount}"
//
//                                Glide.with(this)
//                                    .load(userImage)
//                                    .placeholder(R.drawable.logo_black_primary) // Optional placeholder image while loading
//                                    .error(R.drawable.logo_black_primary) // Optional error image if loading fails
//                                    .into(binding.imageUserShow)
                            }
                        }
                    } else {
                        println("No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    println("Error getting documents: $exception")
                }
        }
    }
}