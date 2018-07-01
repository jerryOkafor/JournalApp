package me.jerryhanks.journalapp.detatil

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import me.jerryhanks.journalapp.data.DataSource
import me.jerryhanks.journalapp.data.db.Note
import me.jerryhanks.journalapp.ui.detail.DetailViewModel
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
import org.mockito.Mockito.anyLong
import org.mockito.MockitoAnnotations


/**
 * @author Po10cio on 30/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */
@RunWith(JUnit4::class)
class DetailViewModelTest : KoinTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    //mock some stuff
    private val dataSource = mock(DataSource::class)
    private val viewModel = DetailViewModel(dataSource)

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testNull() {
        assertThat(viewModel.getNote(), notNullValue())
        assertThat(viewModel.getError(), notNullValue())

        //verify that the repo was never called
        verify(dataSource, never()).getNoteById(anyLong())
    }

    @Test
    fun doNotFetchWhenNotObserved() {
        viewModel.setNoteId(1L)
        verify(dataSource, never()).getNoteById(anyLong())
    }

    @Test
    fun fetchWhenObserved() {
        viewModel.setNoteId(1L)

        //observe forever
        viewModel.getNote().observeForever(mock())

        //check that the repo call was triggered
        verify(dataSource).getNoteById(1L)
    }

    @Test
    fun changeWhileObserved() {
        //start observing
        viewModel.getNote().observeForever(mock())

        //toggle the note id from 1 to  2
        viewModel.setNoteId(1L)
        viewModel.setNoteId(2L)

        //verify that the repository was called for the new note id
        verify(dataSource).getNoteById(1L)
        verify(dataSource).getNoteById(2L)
    }

    @Test
    fun nullNoteId() {
        viewModel.setNoteId(null)
        val noteObserver = mock<Observer<Note>>()

        //observe
        viewModel.getNote().observeForever(noteObserver)

        //verify that onChange fro null was called
        verify(noteObserver).onChanged(null)
    }

}