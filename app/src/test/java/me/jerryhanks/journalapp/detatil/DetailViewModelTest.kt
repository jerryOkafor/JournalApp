package me.jerryhanks.journalapp.detatil

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import me.jerryhanks.journalapp.data.DataSource
import me.jerryhanks.journalapp.ui.detail.DetailViewModel
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.anyLong
import org.mockito.Mockito.mock
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
    private val dataSource = mock(DataSource::class.java)
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

//    @Test
//    fun fetchWenObserved() {
//        viewModel.setNoteId(1L)
//
//        //observe forever
//        viewModel.getNote().observeForever(mock())
//    }

}