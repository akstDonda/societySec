package com.nothing.secad.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.window.SplashScreen
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codebyashish.autoimageslider.AutoImageSlider
import com.codebyashish.autoimageslider.Enums.ImageScaleType
import com.codebyashish.autoimageslider.Models.ImageSlidesModel
import com.google.firebase.firestore.FirebaseFirestore
import com.nothing.secad.Adapter.CategoryAdapter
import com.nothing.secad.R
import com.nothing.secad.model.CategoryModel
import com.nothing.secad.databinding.FragmentDashbordBinding
import com.nothing.secad.meeting.ZoomMainActivity
import java.util.Calendar

class DashbordFragment : Fragment() {

    private var binding: FragmentDashbordBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashbordBinding.inflate(inflater, container, false)
        boxChangeOnDate()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Call your function here
        fireslider()

        // Dummy data for categories
        val categoryList = listOf(
            CategoryModel(R.drawable.money_transfer_image, "meeting"),
            CategoryModel(R.drawable.baseline_add_24, "complain"),
            CategoryModel(R.drawable.plain_dollar, "payment"),
            CategoryModel(R.drawable.money_transfer_image, "Category"),
            CategoryModel(R.drawable.money_transfer_image, "Category"),
            CategoryModel(R.drawable.money_transfer_image, "Category")
        )

        // Create and set up the CategoryAdapter
        val categoryAdapter = CategoryAdapter(categoryList)

        // Set the item click listener for the adapter
        categoryAdapter.onItemClickListener = object : CategoryAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> {
                        Toast.makeText(requireContext(),"this",Toast.LENGTH_SHORT).show()
                        intentFun(ZoomMainActivity::class.java)
                    }
                    1 -> {
                        // Replace the current fragment with XYZFragment
                        Toast.makeText(requireContext(),"this",Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(requireContext(),"this",Toast.LENGTH_SHORT).show()
                    }
                    4 -> {
                        Toast.makeText(requireContext(),"this",Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        // Handle other positions if necessary
                    }
                }
            }
        }

        // Set up the RecyclerView with a GridLayoutManager
        binding?.categoryRv?.apply {
            layoutManager = GridLayoutManager(context, 3) // 3 is the number of columns
            adapter = categoryAdapter
        }
    }

    fun intentFun(destination: Class<*>) {
        val intent = Intent(requireContext(), destination)
        startActivity(intent)
    }

    // ... Rest of your code

    private fun fireslider() {
        val imageSlider: AutoImageSlider = binding?.autoImageSlider ?: return
        val sliderModels = ArrayList<ImageSlidesModel>()
        val db = FirebaseFirestore.getInstance()
        db.collection("sliderImage").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        // Assuming your ImageSlidesModel constructor takes URL and ImageScaleType
                        sliderModels.add(ImageSlidesModel(document.getString("url"), ImageScaleType.FIT))
                        // sliderModels.add(ImageSlidesModel(document.getString("title")))
                    }
                    imageSlider.setImageList(sliderModels, ImageScaleType.FIT)
                } else {
                    // Handle failures
                    val exception = task.exception
                    exception?.printStackTrace()
                }
            }
            .addOnFailureListener { e ->
                // Handle failure separately, if needed
                e.printStackTrace()
            }
    }
    fun boxChangeOnDate(){
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        // Determine which box to show based on the current date
        if (currentDay in 1..5) {
            // Show the first box
            binding?.box1?.visibility = View.VISIBLE
            binding?.box2?.visibility = View.GONE
        } else if (currentDay in 6..31) {
            // Show the second box
            binding?.box1?.visibility = View.GONE
            binding?.box2?.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
