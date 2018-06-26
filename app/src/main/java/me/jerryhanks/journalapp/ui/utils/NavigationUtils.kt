package me.jerryhanks.journalapp.ui.utils

import android.support.v4.app.FragmentActivity
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.ui.auth.AuthFragment
import me.jerryhanks.journalapp.ui.entries.EntriesFragment


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

object NavigationUtils {
    fun gotoSignIn(activity: FragmentActivity) {
        val signInFragment = AuthFragment()
        activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, signInFragment)
                .commitAllowingStateLoss()

    }

    fun gotoEntries(activity: FragmentActivity) {
        val entriesFragment = EntriesFragment()
        activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, entriesFragment)
                .addToBackStack("entries")
                .commitAllowingStateLoss()
    }
}