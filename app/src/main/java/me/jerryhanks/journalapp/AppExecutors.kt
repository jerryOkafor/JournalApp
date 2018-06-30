package me.jerryhanks.journalapp

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors


/**
 * @author Po10cio on 27/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */


/**
 * Provides different threads for the execution of tasks such as diskIO, networkIO and mainThread
 * */

open class AppExecutors(private val diskIO: Executor, private val networkIO: Executor, private val mainThread: Executor) {

    constructor() : this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
            MainThreadExecutor())

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}

