package me.jerryhanks.journalapp.ui.core

import android.arch.core.executor.testing.CountingTaskExecutorRule
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.IdlingResource
import org.junit.runner.Description
import java.util.concurrent.CopyOnWriteArrayList


/*
 * @author Po10cio on 01/07/2018 
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */
/**
 * A Junit rule that registers Architecture Components' background threads as an Espresso idling
 * resource.
 */
class TaskExecutorWithIdlingResourceRule : CountingTaskExecutorRule() {
    private val callbacks = CopyOnWriteArrayList<IdlingResource.ResourceCallback>()

    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(object : IdlingResource {
            override fun getName(): String {
                return "architecture components idling resource"
            }

            override fun isIdleNow(): Boolean {
                return this@TaskExecutorWithIdlingResourceRule.isIdle
            }

            override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
                callbacks.add(callback)
            }
        })
        super.starting(description)
    }

    override fun onIdle() {
        super.onIdle()
        for (callback in callbacks) {
            callback.onTransitionToIdle()
        }
    }
}
