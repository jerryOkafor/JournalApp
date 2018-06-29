package me.jerryhanks.journalapp.ui.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * @author Po10cio on 28/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */
object Util {
    fun closeSoftInputMethod(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }
}