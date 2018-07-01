package me.jerryhanks.journalapp.notes

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import me.jerryhanks.journalapp.data.DataSource
import me.jerryhanks.journalapp.data.db.Note
import me.jerryhanks.journalapp.ui.notes.EntriesViewModel
import me.jerryhanks.journalapp.utils.mock
import org.amshove.kluent.mock
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations


/*
 * @author Po10cio on 01/07/2018 
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@RunWith(JUnit4::class)
class NotesViewModelTest : KoinTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    private val datSource = mock(DataSource::class)

    private lateinit var viewModel: EntriesViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        whenever(datSource.getAllNotes()).thenReturn(mock())
        viewModel = EntriesViewModel(datSource)
//        whenever(viewModel.getNotes()).thenReturn(notes)
    }

    @Test
    fun testNull() {
        assertThat(viewModel.getNotes(), notNullValue())

    }

//    @Test
//    fun testEmptyNotes() {
//        val notesObserver = mock<Observer<PagedList<Note>>>()
//
//        viewModel.getNotes().observeForever(notesObserver)
//
//        verify(notesObserver).onChanged(null)
//    }


}