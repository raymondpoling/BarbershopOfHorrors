package org.mousehole.americanairline.barbershopofhorrors

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.mousehole.americanairline.barbershopofhorrors.Constants.LOG_TAG

class CustomerRecyclerAdapter(private var customers:MutableList<Customer>) : RecyclerView.Adapter<CustomerRecyclerAdapter.CustomerViewHolder>() {
    class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val customerNameTextView : TextView = itemView.findViewById(R.id.customer_name_textview)
        val estimatedTimeTextView : TextView = itemView.findViewById(R.id.estimated_time_textview)
        val orderTextView : TextView = itemView.findViewById(R.id.order_textview)
        val progressBar : ProgressBar = itemView.findViewById(R.id.order_progressbar)
        val barberTextView : TextView = itemView.findViewById(R.id.barber_name_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.customer_item_layout, parent, false)
        return CustomerViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = customers[position]

        holder.apply {
            customerNameTextView.text = customer.name
            estimatedTimeTextView.text = "${customer.estimatedTime}mins"
            orderTextView.text = customer.order
            progressBar.progress = customer.progress
            barberTextView.text = customer.barber
        }

    }

    override fun getItemCount(): Int = customers.size

    fun setCustomer(customer:Customer) {
        val item = customers.find { it.name == customer.name }
        val index = customers.indexOf(item)
//        customers[index] = customer
        Log.d(LOG_TAG, "Index [$index] for customer $customer")
        notifyItemChanged(index)
    }
}