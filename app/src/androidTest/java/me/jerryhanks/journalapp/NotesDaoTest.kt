package me.jerryhanks.journalapp

import android.support.test.runner.AndroidJUnit4
import me.jerryhanks.journalapp.data.db.JournalDb
import me.jerryhanks.journalapp.di.roomTestModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.inject
import org.koin.test.KoinTest


/**
 * @author Po10cio on 27/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@RunWith(AndroidJUnit4::class)
class NotesDaoTest : KoinTest {
    //inject needed component from Koin
    private val journalDb: JournalDb by inject()

    /**
     * Override default Koin config to use In-Memory database
     * */
    @Before
    fun before() {
        loadKoinModules(roomTestModule)
    }


    @Test
    fun testInsertDiary() {
        assert(true)
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