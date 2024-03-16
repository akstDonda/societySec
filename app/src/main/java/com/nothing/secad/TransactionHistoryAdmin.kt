package com.nothing.secad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nothing.secad.Adapter.TransactionHistoryAdapter
import com.nothing.secad.databinding.ActivityTransactionHistoryAdminBinding
import com.nothing.secad.model.TransactionHistoryModel
import java.util.*

class TransactionHistoryAdmin : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionHistoryAdminBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var transactionArrayList: ArrayList<TransactionHistoryModel>
    private lateinit var myAdapter: TransactionHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionHistoryAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.transactionHistoryRv
        recyclerView.layoutManager = LinearLayoutManager(this)
        transactionArrayList = ArrayList()
        myAdapter = TransactionHistoryAdapter(transactionArrayList)
        recyclerView.adapter = myAdapter

        // Call function to add default hardcoded data
        addDefaultData()
    }

    private fun addDefaultData() {
        // Create dummy TransactionHistoryModel objects and add them to the list
        val date1 = Date()
        val transaction1 = TransactionHistoryModel(date1, 1000, true, "1")
        transactionArrayList.add(transaction1)

        val date2 = Date()
        val transaction2 = TransactionHistoryModel(date2, 2000, false, "2")
        transactionArrayList.add(transaction2)

        val date3 = Date()
        val transaction3 = TransactionHistoryModel(date3, 1500, true, "3")
        transactionArrayList.add(transaction3)

        // Notify the adapter that the data set has changed
        myAdapter.notifyDataSetChanged()
    }
}


