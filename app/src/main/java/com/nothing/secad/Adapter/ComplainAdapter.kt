package com.nothing.secad.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nothing.secad.R
import com.nothing.secad.model.complainModel
import java.text.SimpleDateFormat
import java.util.*

class ComplainAdapter(private val complainList: List<complainModel>) :
    RecyclerView.Adapter<ComplainAdapter.ComplainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplainViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_complain, parent, false)
        return ComplainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ComplainViewHolder, position: Int) {
        val currentItem = complainList[position]
        holder.imgView.setImageResource(currentItem.imgUrl)
        holder.typeTextView.text = "Type: ${currentItem.type}"
        holder.titleTextView.text = "Title: ${currentItem.title}"
        holder.dateTextView.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(currentItem.date)
        holder.statusTextView.text = "Status: ${currentItem.status}"
        holder.descTextView.text = "Description: ${currentItem.description}"

        // Implement button click listeners here if needed
        holder.aproveButton.setOnClickListener {
            // Handle approve button click
        }

        holder.unAproveButton.setOnClickListener {
            // Handle unapprove button click
        }
    }

    override fun getItemCount() = complainList.size

    inner class ComplainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var imgView:ImageView = itemView.findViewById(R.id.complain_history_img)
        var typeTextView:TextView = itemView.findViewById(R.id.complain_history_type)
        var titleTextView:TextView = itemView.findViewById(R.id.complain_history_title)
        var dateTextView:TextView = itemView.findViewById(R.id.complain_history_date)
        var statusTextView:TextView = itemView.findViewById(R.id.complain_history_status)
        var descTextView:TextView = itemView.findViewById(R.id.complain_history_desc)
        var aproveButton:Button = itemView.findViewById(R.id.complain_aproove_btn)
        var unAproveButton:Button = itemView.findViewById(R.id.complain_un_aproove_btn)


    }
}
