package me.jerryhanks.journalapp.ui.utils

import android.support.v4.app.Fragment
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author Po10cio on 29/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

fun Date.toFormattedString(): String {
    val simpleDateFormat = SimpleDateFormat.getDateInstance()
    return simpleDateFormat.format(this)
}

fun Fragment.goBack() {
    requireActivity().supportFragmentManager.popBackStack()
}