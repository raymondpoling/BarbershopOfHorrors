package org.mousehole.americanairline.barbershopofhorrors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), Handler.Callback {

    private lateinit var customerList : RecyclerView
    private lateinit var adapter : CustomerRecyclerAdapter

    private lateinit var handler: Handler

    val customerWork : MutableList<CustomerWork> by lazy {
        mutableListOf<CustomerWork>(
            CustomerWork(Customer("James", "Beard Trim", 12), handler),
            CustomerWork(Customer("Jamie", "Shampoo and Haircut", 17), handler),
            CustomerWork(Customer("Gladys", "Shampoo, Hair Coloring, and Perm", 44), handler),
            CustomerWork(Customer("Jody", "Trim and Styling", 18), handler),
            CustomerWork(Customer("Gregory", "Eyebrow Wax", 7), handler),
            CustomerWork(Customer("Jose", "Shampoo and Styling", 22), handler),
            UnhappyCustomer(Customer("Black Widow", "Shampoo and Styling", 12), handler),
            CustomerWork(Customer("Janine", "The Works", 33), handler),
            CustomerWork(Customer("Francesca", "Styling", 14), handler),
            CustomerWork(Customer("Jimmie", "Military Cut", 15), handler),
            UnhappyCustomer(Customer("The Hulk", "Shampoo and Styling", 14), handler),
            CustomerWork(Customer("Cowen", "Military Cut", 13), handler),
            CustomerWork(Customer("Jessica", "Military Style", 17), handler)
    )
    }

    private lateinit var customers : MutableList<Customer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler(Looper.getMainLooper(), this)

        customers = customerWork.map { it.customer }.toMutableList()

        customerList = findViewById(R.id.customer_recyclerview)
        adapter = CustomerRecyclerAdapter(customers)
        customerList.adapter = adapter

        BarberShop(handler, customerWork)

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