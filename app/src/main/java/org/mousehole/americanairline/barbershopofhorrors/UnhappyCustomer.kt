package org.mousehole.americanairline.barbershopofhorrors

import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import org.mousehole.americanairline.barbershopofhorrors.Constants.LOG_TAG

class UnhappyCustomer(customer: Customer, handler : Handler) : CustomerWork(customer, handler) {
    private var workDone : Int = 0
    override fun getWorkDone() = workDone
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
        customer.barber = "${customer.name} beat up ${Thread.currentThread().name}!"
        customer.progress = 100
        sendCustomer(customer)
        val mp = MediaPlayer.create(MainActivity.getContext(), R.raw.rhodes)
        mp.start()
        Log.d(LOG_TAG, "${customer.name} hates what ${Thread.currentThread().name} has done with their hair, and injures them terribly.")
        throw Exception("Killing thread.")
    }
}