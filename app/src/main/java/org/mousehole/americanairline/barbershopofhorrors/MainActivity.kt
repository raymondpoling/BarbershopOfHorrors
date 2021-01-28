package org.mousehole.americanairline.barbershopofhorrors

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator

class MainActivity : AppCompatActivity(), Handler.Callback, CustomerWork.ContextDelegate {

    override fun getContext() : Context = this
    
    private lateinit var customerList : RecyclerView
    private lateinit var adapter : CustomerRecyclerAdapter

    private lateinit var handler: Handler

    private val customerWork : MutableList<CustomerWork> by lazy {
        mutableListOf(
            CustomerWork(Customer("James", "Beard Trim", 12), handler, this),
            CustomerWork(Customer("Jamie", "Shampoo and Haircut", 17), handler, this),
            CustomerWork(Customer("Gladys", "Shampoo, Hair Coloring, and Perm", 44), handler, this),
            CustomerWork(Customer("Jody", "Trim and Styling", 18), handler, this),
            CustomerWork(Customer("Gregory", "Eyebrow Wax", 7), handler, this),
            CustomerWork(Customer("Jose", "Shampoo and Styling", 22), handler, this),
            UnhappyCustomer(Customer("Black Widow", "Shampoo and Styling", 12), handler, this),
            CustomerWork(Customer("Janine", "The Works", 33), handler, this),
            CustomerWork(Customer("Francesca", "Styling", 14), handler, this),
            CustomerWork(Customer("Jimmie", "Military Cut", 15), handler, this),
            UnhappyCustomer(Customer("The Hulk", "Shampoo and Styling", 14), handler, this),
            CustomerWork(Customer("Cowen", "Military Cut", 13), handler, this),
            CustomerWork(Customer("Jessica", "Military Style", 17), handler, this)
    )
    }

    private lateinit var customers : MutableList<Customer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler(Looper.getMainLooper(), this)

        customers = customerWork.map { it.customer }.toMutableList()

        customerList = findViewById(R.id.customer_recyclerview)
        (customerList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        adapter = CustomerRecyclerAdapter(customers)
        customerList.adapter = adapter

        BarberShop(customerWork)

    }

    override fun handleMessage(msg: Message): Boolean {
        val customer : Customer? = msg.data.getParcelable("customer")
        customer?.let {
            runOnUiThread {
                adapter.setCustomer(customer)
            }
            return true
        }
        return false
    }
}