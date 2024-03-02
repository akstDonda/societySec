package com.nothing.secad.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nothing.secad.R
import com.nothing.secad.model.TransactionHistoryModel

class TransactionHistoryAdapter(private val transactionHistory: ArrayList<TransactionHistoryModel>) :
    RecyclerView.Adapter<TransactionHistoryAdapter.TransactionHistoryViewHolder>() {

    inner class TransactionHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.transaction_history_date)
        val amountTextView: TextView = itemView.findViewById(R.id.transaction_history_amount)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionHistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_history_single_item, parent, false)
        return TransactionHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionHistoryViewHolder, position: Int) {
        val currentItem = transactionHistory[position]
        holder.dateTextView.text = "Date: ${currentItem.Date}"
        holder.amountTextView.text = "Amount: ${currentItem.Ammount}"
    }

    override fun getItemCount(): Int {
        return transactionHistory.size
    }
}
