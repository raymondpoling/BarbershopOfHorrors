package org.mousehole.americanairline.barbershopofhorrors

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import org.mousehole.americanairline.barbershopofhorrors.Constants.LOG_TAG

open class CustomerWork(val customer: Customer, private val handler: Handler) : Runnable {
    private var workDone : Int = 0
    open fun getWorkDone() = workDone
    override fun run() {
        customer.barber = Thread.currentThread().name
        Log.d(LOG_TAG, "${customer.name} has come to have ${customer.order} done, by ${Thread.currentThread().name}.")
        sendCustomer(customer)
        while(workDone < customer.estimatedTime) {
            Thread.sleep(1000 * 1)
            workDone += 1
//            if(workDone % 5 == 0 && workDone != 0) {
                customer.progress = ((workDone.toDouble() / customer.estimatedTime.toDouble()) * 100).toInt()
                sendCustomer(customer)
//                Log.d(LOG_TAG, "${Thread.currentThread().name} has worked on ${customer.name} for ${workDone}mins.")
//            }
        }
        Log.d(LOG_TAG, "${customer.name} has finished their appointment, and ${Thread.currentThread().name} will take another customer.")
        customer.progress = 100
        customer.barber = "Done by ${Thread.currentThread().name}"
        val mp = MediaPlayer.create(MainActivity.getContext(), R.raw.blip)
        mp.start()
        sendCustomer(customer)
    }

    protected fun sendCustomer(customer: Customer) {
        val bundle = Bundle()
        bundle.putParcelable("customer",customer)
        val msg = Message()
        msg.data = bundle
        handler.dispatchMessage(msg)
    }
}