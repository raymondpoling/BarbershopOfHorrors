package org.mousehole.americanairline.barbershopofhorrors

import android.util.Log
import org.mousehole.americanairline.barbershopofhorrors.Constants.LOG_TAG
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

class BarberFactory : ThreadFactory {

    private val id: AtomicInteger = AtomicInteger(0)
    private val names: List<String> = listOf("Barney", "Fred", "Wilma", "Betty", "BamBam", "Pebbles", "Dino", "Hoppy")

    override fun newThread(r: Runnable?): Thread {
        val thread = Thread(r)
        thread.name = names[id.getAndIncrement() % names.size]
        Log.d(LOG_TAG, "${thread.name} is getting ready for work.")
        thread.uncaughtExceptionHandler = Thread.UncaughtExceptionHandler { t, e ->
            Log.d(LOG_TAG, "${thread.name} was forced to leave work early!")
            return@UncaughtExceptionHandler
        }
        return thread
    }

}