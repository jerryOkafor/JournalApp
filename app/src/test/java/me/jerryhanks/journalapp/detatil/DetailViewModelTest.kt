package me.jerryhanks.journalapp.detatil

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import me.jerryhanks.journalapp.data.Repository
import me.jerryhanks.journalapp.ui.detail.DetailViewModel
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest
import org.mockito.Mockito.anyLong
import org.mockito.Mockito.mock


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
    private val dataSource = mock(Repository::class.java)
    private val viewModel = DetailViewModel(dataSource)

    @Test
    fun testNull() {
        assertThat(viewModel.getNote(), notNullValue())
        assertThat(viewModel.getError(), notNullValue())

        verify(dataSource, never()).getNoteById(anyLong())
    }

}