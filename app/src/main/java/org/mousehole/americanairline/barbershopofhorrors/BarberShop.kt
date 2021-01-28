package org.mousehole.americanairline.barbershopofhorrors

import android.os.Handler
import java.util.concurrent.*

class BarberShop(handler: Handler, customers : List<CustomerWork>) {

    private val NUM_THREADS = 4
    private val MAX_THREADS = 4
    private val TIMEOUT = 1000L

    private val queue : BlockingDeque<Runnable> = LinkedBlockingDeque(10)
    private val barberPool : ThreadPoolExecutor =
            ThreadPoolExecutor(NUM_THREADS,
                    MAX_THREADS,
                    TIMEOUT,
                    TimeUnit.SECONDS,
                    queue,
                    BarberFactory())

    init {
        customers.forEach (barberPool::execute)
    }


}