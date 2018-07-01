package me.jerryhanks.journalapp.utils

import android.arch.lifecycle.LiveData
import me.jerryhanks.journalapp.data.db.Note
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Po10cio on 30/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

object TestUtils {
    val DURATION_SHORT = 3
    val DURATION_MEDIUM = 6
    val DURATION_LONG = 12

    val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val TEST_NOTE_CREATED_DATE = DATE_FORMAT.parse("2018-06-30")
    val TEST_NOTE_UPDATED_DATE = DATE_FORMAT.parse("2018-07-01")
    const val TEST_NOTE_ID = 1L
    const val TEST_NOTE_TITLE = "Sharing code between unit tests and instrumentation tests on Android"
    const val TEST_NOT_CONTENT = "Suppose you've got an Android application with a bunch of tests. Some of them are unit tests (located in src/test). The rest are instrumentation tests (located in src/androidTest)." +
            "Here's the dilemma: you've got some utility code that you'd like all of your tests to share but src/test can't use code from src/androidTest and vice versa. You could put that code into src/main but you want to avoid shipping test code. How else do you share the code between the tests?" +
            "The solution I've come up with is to leverage source sets to define common code. First, I put my shared test code into src/sharedTest/java1 . Then I added the following code to build.gradle:"

    @JvmStatic
    fun createNote(): Note {
        return Note(TEST_NOTE_ID, TEST_NOTE_TITLE, TEST_NOT_CONTENT, TEST_NOTE_CREATED_DATE, TEST_NOTE_UPDATED_DATE)
    }

    @JvmStatic
    fun createLiveNote(): LiveData<Note> {
        return object : LiveData<Note>() {
            override fun onActive() {
                super.onActive()
                postValue(createNote())
            }
        }
    }


    fun sleep(seconds: Int) {
        try {
            Thread.sleep((seconds * 1000).toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}