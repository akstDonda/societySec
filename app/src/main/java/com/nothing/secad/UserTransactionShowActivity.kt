package com.nothing.secad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nothing.secad.Adapter.UserTransactionAdapter
import com.nothing.secad.databinding.ActivityUserTransactionShowBinding
import com.nothing.secad.model.userTransaction
import java.util.*

class UserTransactionShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserTransactionShowBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserTransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserTransactionShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.RVUserTransactionShow
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dummyData = generateDummyData()
        adapter = UserTransactionAdapter(dummyData)
        recyclerView.adapter = adapter
    }

    private fun generateDummyData(): List<userTransaction> {
        val dummyList = ArrayList<userTransaction>()
        dummyList.add(userTransaction(Date(), 1000, true, "5050"))
        dummyList.add(userTransaction(Date(), 2000, false, "2020"))
        // Add more dummy data if needed
        return dummyList
    }
}
