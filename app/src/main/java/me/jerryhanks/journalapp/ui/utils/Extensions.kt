package me.jerryhanks.journalapp.ui.utils

import java.text.SimpleDateFormat
import java.util.*


/**
 * @author Po10cio on 29/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

fun Date.toFormatedString(): String {
    val simpleDateFormat = SimpleDateFormat.getDateInstance()
    return simpleDateFormat.format(this)
}