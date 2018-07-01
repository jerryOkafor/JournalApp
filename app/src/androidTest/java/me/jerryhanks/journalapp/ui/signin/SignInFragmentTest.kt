package me.jerryhanks.journalapp.ui.signin

import android.support.annotation.CallSuper
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.testing.SingleFragmentActivity
import me.jerryhanks.journalapp.ui.core.BaseTest
import me.jerryhanks.journalapp.ui.signIn.SignInFragment
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


/*
 * @author Po10cio on 01/07/2018 
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SignInFragmentTest : BaseTest<SingleFragmentActivity>(SingleFragmentActivity::class.java) {

    lateinit var signInFragment: SignInFragment

    @Before
    @CallSuper
    fun init() {
        //init mock
//        MockitoAnnotations.initMocks(this)

        signInFragment = SignInFragment()

        activityTestRule.launchActivity(null)
        activityTestRule.activity.setFragment(signInFragment)

    }

    @Test
    fun checkGoogleSignInIsDisplayed() {
        val googleSignInButton = onView(withId(R.id.signInButton))
        googleSignInButton.check(matches(isDisplayed()))
    }

    @Test
    fun checkThatCallToActionTextViewIsDisplayed() {
        val callToActionTextView = onView(withId(R.id.tvCallToAction))
        callToActionTextView.check(matches(isDisplayed()))
    }

    @Test
    fun checkThatProgressBarIsInitiallyHidden() {
        val progressbar = onView(withId(R.id.progressBar))

        progressbar.check(matches(not(isDisplayed())))

    }

//    @Test
//    fun testSign() {
//
//    }
}