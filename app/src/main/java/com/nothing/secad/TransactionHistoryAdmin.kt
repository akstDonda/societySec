package com.nothing.secad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.auth.User
import com.nothing.secad.Adapter.TransactionHistoryAdapter
import com.nothing.secad.databinding.ActivityTransactionHistoryAdminBinding
import com.nothing.secad.model.TransactionHistoryModel

class TransactionHistoryAdmin : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionHistoryAdminBinding
    private lateinit var recyclerView: RecyclerView;
    private lateinit var TransactionArrayList: ArrayList<TransactionHistoryModel>
    private  lateinit var myAdapter: TransactionHistoryAdapter
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionHistoryAdminBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.transactionHistoryRv
        recyclerView.layoutManager  = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        TransactionArrayList = arrayListOf()
        myAdapter = TransactionHistoryAdapter(TransactionArrayList)
        recyclerView.adapter = myAdapter

        EventChangeListner()
    }

    private fun EventChangeListner(){
        //TODO: fire base code right
    }

}

