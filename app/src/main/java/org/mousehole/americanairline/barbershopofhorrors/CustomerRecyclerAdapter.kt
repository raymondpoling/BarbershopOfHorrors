package org.mousehole.americanairline.barbershopofhorrors

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.mousehole.americanairline.barbershopofhorrors.Constants.LOG_TAG

class CustomerRecyclerAdapter(var customers:MutableList<Customer>) : RecyclerView.Adapter<CustomerRecyclerAdapter.CustomerViewHolder>() {
    class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val barbershopCardView : BarbershopCardView = itemView.findViewById(R.id.barbershop_cardview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.customer_item_layout, parent, false)
        return CustomerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = customers[position]

        holder.barbershopCardView.apply {
            setCustomerName(customer.name)
            setEstimatedTime(customer.estimatedTime)
            setOrder(customer.order)
            setProgress(customer.progress)
            setBarber(customer.barber)
        }

    }

    override fun getItemCount(): Int = customers.size

    fun setCustomer(customer:Customer) {
        val item = customers.find { it.name == customer.name }
        val index = customers.indexOf(item)
        customers[index] = customer
        Log.d(LOG_TAG, "Index [$index] for customer $customer")
        notifyItemChanged(index)
    }
}