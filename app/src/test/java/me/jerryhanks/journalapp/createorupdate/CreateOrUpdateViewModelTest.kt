package me.jerryhanks.journalapp.createorupdate

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import me.jerryhanks.journalapp.data.DataSource
import me.jerryhanks.journalapp.data.db.Note
import me.jerryhanks.journalapp.ui.createorupdate.CreateOrUpdateViewModel
import me.jerryhanks.journalapp.utils.mock
import org.amshove.kluent.mock
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest
import org.mockito.ArgumentMatchers.anyLong


/*
 * @author Po10cio on 01/07/2018 
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */
@RunWith(JUnit4::class)
class CreateOrUpdateViewModelTest : KoinTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    //mock some stuff
    private val dataSource = mock(DataSource::class)
    private val viewModel = CreateOrUpdateViewModel(dataSource)

    @Test
    fun testNull() {
        assertThat(viewModel.getNote(), notNullValue())

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

        //observe
        viewModel.getNote().observeForever(mock())

        //verify that datasource was called
        verify(dataSource).getNoteById(anyLong())

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