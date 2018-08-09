package me.jerryhanks.journalapp.ui.detail

import android.arch.lifecycle.MutableLiveData
import android.support.annotation.CallSuper
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import me.jerryhanks.journalapp.data.db.Note
import me.jerryhanks.journalapp.testing.SingleFragmentActivity
import me.jerryhanks.journalapp.ui.core.BaseTest
import me.jerryhanks.journalapp.utils.TestUtils
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


/*
 * @author Po10cio on 01/07/2018 
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class DetailFragmentTest : BaseTest<SingleFragmentActivity>(SingleFragmentActivity::class.java) {

    lateinit var detailsFragment: DetailFragment

    private val note = MutableLiveData<Note>()
    private val error = MutableLiveData<String>()

    @Before
    @CallSuper
    fun init() {
        //init mock
        MockitoAnnotations.initMocks(this)
        detailsFragment = DetailFragment.newInstance(TestUtils.TEST_NOTE_ID)

        activityTestRule.launchActivity(null)
        activityTestRule.activity.setFragment(detailsFragment)

        `when`(detailViewModel.getNote()).thenReturn(note)
        `when`(detailViewModel.getError()).thenReturn(error)

    }

    @Test
    fun getError_Error_shouldShowSnackBar() {
        //pos error value
        error.postValue("Error")
        
//        onView(withId(android.support.compat.sn))
    }

}