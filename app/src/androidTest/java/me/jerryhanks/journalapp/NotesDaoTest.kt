package me.jerryhanks.journalapp

import android.app.Application
import android.database.SQLException
import android.support.test.runner.AndroidJUnit4
import me.jerryhanks.journalapp.data.db.JournalDb
import me.jerryhanks.journalapp.di.roomTestModule
import me.jerryhanks.journalapp.utils.LiveDataUtils
import me.jerryhanks.journalapp.utils.TestUtils
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.with
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.Mockito.mock


/**
 * @author Po10cio on 27/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@RunWith(AndroidJUnit4::class)
class NotesDaoTest : KoinTest {
    //inject needed component from Koin
    val journalDb: JournalDb by inject()

    /**
     * Override default Koin config to use In-Memory database
     * */
    @Before
    fun before() {
        loadKoinModules(roomTestModule) with mock(Application::class.java)
    }


    @Test
    @Throws(SQLException::class)
    fun testInsertOrUpdateNote_doesNotExist() {
        val result = journalDb.notesDao().insertOrUpdateNote(TestUtils.createNote())

        assertThat(result, `is`(TestUtils.TEST_NOTE_ID))

        //try to load the newly inserted note
        val loadedNote = LiveDataUtils.getValue(journalDb.notesDao().getNoteById(TestUtils.TEST_NOTE_ID))

        assertThat(loadedNote.id, `is`(TestUtils.TEST_NOTE_ID))
        assertThat(loadedNote.title, `is`(TestUtils.TEST_NOTE_TITLE))
        assertThat(loadedNote.createdAt, `is`(TestUtils.TEST_NOTE_CREATED_DATE))
    }

    @Test
    @Throws(SQLException::class)
    fun testInsertOrUpdateNote_exists() {
        val note = TestUtils.createNote()
        val testTitle = "This is a new title"

        //insert the first note
        val result = journalDb.notesDao().insertOrUpdateNote(note)

        //ensure that it was added successfully
        assertThat(result, `is`(TestUtils.TEST_NOTE_ID))

        //insert an updated copy of the note
        val updated = note.copy(title = testTitle)
        journalDb.notesDao().insertOrUpdateNote(updated)

        //get the updated note
        val loaded = LiveDataUtils.getValue(journalDb.notesDao().getNoteById(TestUtils.TEST_NOTE_ID))

        //check that thw id was nor replaced
        assertThat(loaded.id, `is`(updated.id))

        //assert that the title was replaced
        assertThat(loaded.title, `is`(testTitle))

    }


    /**
     * Clean resource
     * */
    @After
    fun after() {
        journalDb.close()
        closeKoin()
    }
}