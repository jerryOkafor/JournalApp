package me.jerryhanks.journalapp.ui.core

import android.app.Activity
import android.support.annotation.CallSuper
import android.support.test.rule.ActivityTestRule
import me.jerryhanks.journalapp.di.appModule
import me.jerryhanks.journalapp.utils.TestUtils
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.Koin
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.test.KoinTest
import java.io.File


/*
 * @author Po10cio on 01/07/2018 
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

abstract class BaseTest<T : Activity>(clazz: Class<T>) {

    @Rule
    @JvmField
    val activityTestRule: ActivityTestRule<T> = ActivityTestRule(clazz, true, false)

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    val checkThat: Matchers = Matchers()

    @CallSuper
    @Before
    fun setUp() {
        val file = File("/sdcard/tmp/code-coverage/connected")
        if (!file.exists()) file.mkdirs()
        TestUtils.sleep(TestUtils.DURATION_MEDIUM)
    }

    @CallSuper
    @After
    fun tearDown() {
    }
}