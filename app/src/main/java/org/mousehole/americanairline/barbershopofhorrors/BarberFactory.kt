package org.mousehole.americanairline.barbershopofhorrors

import android.util.Log
import org.mousehole.americanairline.barbershopofhorrors.Constants.LOG_TAG
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

class BarberFactory : ThreadFactory {

    val id: AtomicInteger = AtomicInteger(0)
    val names: List<String> = listOf("Barney", "Fred", "Wilma", "Betty", "BamBam", "Pebbles", "Dino", "Hoppy")

    override fun newThread(r: Runnable?): Thread {
        val thread = Thread(r)
        thread.name = names[id.getAndIncrement() % names.size]
        Log.d(LOG_TAG, "${thread.name} is getting ready for work.")
        thread.uncaughtExceptionHandler = object : Thread.UncaughtExceptionHandler {
            override fun uncaughtException(t: Thread, e: Throwable) {
                Log.d(LOG_TAG, "${thread.name} was forced to leave work early!")
                return
            }

        }
        return thread
    }

}