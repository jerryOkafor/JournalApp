package me.jerryhanks.journalapp.ui.utils

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.ui.entries.EntriesFragment
import me.jerryhanks.journalapp.ui.entrydetails.DetailsFragment
import me.jerryhanks.journalapp.ui.signIn.SignInFragment


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

object NavigationUtils {
    fun gotoSignIn(activity: FragmentActivity) {
        val signInFragment = SignInFragment()
        val supportFragmentManager = activity.supportFragmentManager

        //pop the entries fragment if it exists
        supportFragmentManager.popBackStackImmediate("entries", FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, signInFragment)
                .addToBackStack("signIn")
                .commitAllowingStateLoss()

    }

    fun gotoEntries(activity: FragmentActivity) {
        val entriesFragment = EntriesFragment()
        val supportFragmentManager = activity.supportFragmentManager

        //pop the signIn Fragment
        supportFragmentManager.popBackStackImmediate("signIn", FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, entriesFragment)
                .addToBackStack("entries")
                .commitAllowingStateLoss()
    }

    fun gotoDetails(activity: FragmentActivity) {
        val supportFragmentManager = activity.supportFragmentManager

        val detailFragment = DetailsFragment.newInstance("")
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailFragment)
                .addToBackStack("details")
                .commitAllowingStateLoss()

    }

}