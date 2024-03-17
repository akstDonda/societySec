package com.nothing.secad.complain


import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.nothing.secad.Adapter.ComplainAdapter
import com.nothing.secad.R
import com.nothing.secad.databinding.ActivityComplainBinding
import com.nothing.secad.model.complainModel
import java.util.*

class ComplainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityComplainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create dummy data
        var dummyData = listOf<complainModel>()


        // Set up RecyclerView
        val layoutManager = LinearLayoutManager(this)
        binding.complainRv.layoutManager = layoutManager
        val adapter = ComplainAdapter(dummyData, this)
        binding.complainRv.adapter = adapter

        fun updateView() {
            adapter.addData(dummyData)
        }

        Firebase.firestore.collection("societies").document(Firebase.auth.currentUser!!.uid).collection("complains").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val complainId = document.id
                    val data = document.data

                    var status = "pending"
                    if (data.get("resolved").toString().toBoolean()) {
                        status = "resolved"
                    }
                    else if (data.get("approved").toString().toBoolean()) {
                        status = "approved"
                    }
                    else if (data.get("rejected").toString().toBoolean()) {
                        status = "rejected"
                    }

                    var date = data.get("timestamp") as Timestamp
                    var newDate = date.toDate()
                    dummyData = dummyData + complainModel(
                        data.get("imageUrl").toString(),
                        data.get("type").toString(),
                        data.get("title").toString(),
                        newDate,  // TODO: update date
                        status,
                        data.get("description").toString(),
                        complainId
                    )
                }
                updateView()
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }
}
