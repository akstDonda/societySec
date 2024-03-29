package com.nothing.secad.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nothing.secad.R
import com.nothing.secad.model.TransactionHistoryModel
import java.text.SimpleDateFormat
import java.util.*

class TransactionHistoryAdapter(private var transactionList: MutableList<TransactionHistoryModel>) :
    RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder>() {

        var transactionListMaster = transactionList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_history_single_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val transactionDate: TextView = itemView.findViewById(R.id.transaction_date)
        private val transactionAmount: TextView = itemView.findViewById(R.id.transaction_amount)
        private val transactionStatus: TextView = itemView.findViewById(R.id.transaction_status)
        private val transactionToPayBtn: Button = itemView.findViewById(R.id.transaction_to_pay_btn)

        fun bind(transaction: TransactionHistoryModel) {
            val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
            val formattedDate = dateFormat.format(transaction.date)
            transactionDate.text = "Date: $formattedDate"
            transactionAmount.text = "Amount: ${transaction.amount}"
            transactionStatus.text = "Status: ${if (transaction.status) "Paid" else "Pending"}"
            transactionToPayBtn.setOnClickListener {
                // Handle button click here
            }
        }
    }

    fun updateTransactionList(updatedList: MutableList<TransactionHistoryModel>) {
        transactionList = updatedList
        transactionListMaster = updatedList
        notifyDataSetChanged()
    }
}
