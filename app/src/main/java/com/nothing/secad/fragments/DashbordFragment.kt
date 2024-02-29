package com.nothing.secad.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codebyashish.autoimageslider.AutoImageSlider
import com.codebyashish.autoimageslider.Enums.ImageScaleType
import com.codebyashish.autoimageslider.Models.ImageSlidesModel
import com.google.firebase.firestore.FirebaseFirestore
import com.nothing.secad.databinding.FragmentDashbordBinding

class DashbordFragment : Fragment() {

    private var binding: FragmentDashbordBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashbordBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Call your function here
        fireslider()
    }

    // ... Rest of your code

    private fun fireslider() {
        val imageSlider: AutoImageSlider = binding?.autoImageSlider ?: return
        val sliderModels = ArrayList<ImageSlidesModel>()
        var db = FirebaseFirestore.getInstance()
        db.collection("sliderImage").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        // Assuming your ImageSlidesModel constructor takes URL and ImageScaleType
                        sliderModels.add(ImageSlidesModel(document.getString("url"), ImageScaleType.FIT))
//                        sliderModels.add(ImageSlidesModel(document.getString("title")))
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
