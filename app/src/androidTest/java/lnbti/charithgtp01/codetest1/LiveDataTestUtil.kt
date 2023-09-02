package lnbti.charithgtp01.codetest1

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


object LiveDataTestUtil{
    /**
     * Gets the value of a [LiveData] or waits for it to have one, with a timeout.
     *
     * Use this extension from host-side (JVM) tests. It's recommended to use it alongside
     * `InstantTaskExecutorRule` or a similar mechanism to execute tasks synchronously.
     */
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(value: T) {
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            this.removeObserver(observer)
            throw TimeoutException("LiveData value was never set.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }

    /**
     * Observes a [LiveData] until the `block` is done executing.
     */
    suspend fun <T> LiveData<T>.observeForTesting(block: suspend () -> Unit) {
        val observer = Observer<T> { }
        try {
            observeForever(observer)
            block()
        } finally {
            removeObserver(observer)
        }
    }

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T? {
        var data: T? = null
        val latch = CountDownLatch(1)

        val observer = { t: T ->
            data = t
            latch.countDown()
        }

        liveData.observeForever(observer)

        try {
            // Wait for the LiveData to emit a value
            if (!latch.await(2, TimeUnit.SECONDS)) {
                throw TimeoutException("LiveData value was never set.")
            }
        } finally {
            liveData.removeObserver(observer)
        }

        return data
    }
}
