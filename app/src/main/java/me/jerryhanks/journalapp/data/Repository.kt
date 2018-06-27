package me.jerryhanks.journalapp.data

import me.jerryhanks.journalapp.AppExecutors
import me.jerryhanks.journalapp.data.db.JournalDb


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

class Repository(val roomDb: JournalDb,
                 val appExecutors: AppExecutors) : DataSource {

}