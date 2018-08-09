package me.jerryhanks.journalapp.ui.core

import android.app.Activity
import android.support.annotation.CallSuper
import android.support.test.rule.ActivityTestRule
import me.jerryhanks.journalapp.ui.detail.DetailViewModel
import me.jerryhanks.journalapp.utils.TestUtils
import org.amshove.kluent.mock
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.test.KoinTest
import java.io.File


/*
 * @author Po10cio on 01/07/2018 
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

abstract class BaseTest<T : Activity>(clazz: Class<T>) : KoinTest {
    val detailViewModel = mock(DetailViewModel::class)

    @Rule
    @JvmField
    val activityTestRule = object : ActivityTestRule<T>(clazz, true, false) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()

            // This will replace the UserDataSource module previously defined
            // in the Application class creation before the activity is launched
            // Otherwise ... components won't be reloaded
//            val mockedUserModule = applicationContext {
//                bean { detailViewModel }
//            }
//            loadKoinModules(listOf(mockedUserModule))
            declareMock<DetailViewModel>()

        }
    }

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