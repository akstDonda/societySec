package com.nothing.secad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.nothing.secad.Adapter.CategoryAdapter
import com.nothing.secad.databinding.ActivityMoreCategoryBinding
import com.nothing.secad.model.CategoryModel

class MoreCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoreCategoryBinding
//    private lateinit var binding: ActivityMoreCategotyBinding
    private lateinit var homeCategoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //user
        val dummyDataList = ArrayList<CategoryModel>()
        dummyDataList.add(CategoryModel(R.drawable.pay_icon, "Pay"))
        dummyDataList.add(CategoryModel(R.drawable.baseline_add_complain_24, "complain"))
        dummyDataList.add(CategoryModel(R.drawable.baseline_meeting_24, "Meeting"))
        dummyDataList.add(CategoryModel(R.drawable.baseline_chat_24, "Chat"))


        homeCategoryAdapter = CategoryAdapter(dummyDataList)
        binding.connectUserRv.layoutManager = GridLayoutManager(this, 4)
        binding.connectUserRv.adapter = homeCategoryAdapter

        //admin
        val dummyDataList2 = ArrayList<CategoryModel>()
        dummyDataList2.add(CategoryModel(R.drawable.pay_icon, "Pay"))
//        dummyDataList2.add(CategoryModel(R.drawable.baseline_add_complain_24, "complain"))
        dummyDataList2.add(CategoryModel(R.drawable.baseline_meeting_24, "Meeting"))
        dummyDataList2.add(CategoryModel(R.drawable.baseline_chat_24, "Chat"))


        homeCategoryAdapter = CategoryAdapter(dummyDataList2)
        binding.connectAdminRv.layoutManager = GridLayoutManager(this, 4)
        binding.connectAdminRv.adapter = homeCategoryAdapter

        //other
        val dummyDataList3 = ArrayList<CategoryModel>()
        dummyDataList3.add(CategoryModel(R.drawable.baseline_change_password_24, "change"))
//        dummyDataList2.add(CategoryModel(R.drawable.baseline_add_complain_24, "complain"))
        dummyDataList3.add(CategoryModel(R.drawable.baseline_event_note_24, "note"))
        dummyDataList3.add(CategoryModel(R.drawable.outline_info_24, "info"))


        homeCategoryAdapter = CategoryAdapter(dummyDataList3)
        binding.connectOtherRv.layoutManager = GridLayoutManager(this, 4)
        binding.connectOtherRv.adapter = homeCategoryAdapter
    }
}