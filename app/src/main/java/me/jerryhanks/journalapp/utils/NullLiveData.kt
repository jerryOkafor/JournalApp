package me.jerryhanks.journalapp.utils

import android.arch.lifecycle.LiveData

/**
 * A LiveData class that has `null` value.
 */
class NullLiveData<T : Any?> private constructor() : LiveData<T>() {
    init {
        // use post instead of set since this can be created on any thread
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return NullLiveData()
        }
    }
}
