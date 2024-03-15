package com.nothing.secad.complain


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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
        val dummyData = listOf(
            complainModel(
                R.drawable.money_transfer_image,
                "Type 1",
                "Title 1",
                Date(),
                "Complete",
                "Description 1"
            ),
            complainModel(
                R.drawable.baseline_content_24,
                "Type 2",
                "Title 2",
                Date(),
                "Pending",
                "Description 2"
            ),
            // Add more dummy data as needed
        )

        // Set up RecyclerView
        val layoutManager = LinearLayoutManager(this)
        binding.complainRv.layoutManager = layoutManager
        val adapter = ComplainAdapter(dummyData)
        binding.complainRv.adapter = adapter
    }
}
