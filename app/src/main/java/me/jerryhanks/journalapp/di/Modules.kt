package me.jerryhanks.journalapp.di

import me.jerryhanks.journalapp.data.DataSource
import me.jerryhanks.journalapp.data.Repository
import me.jerryhanks.journalapp.ui.utils.NavigationUtils
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

val appModule: Module = applicationContext {
    bean { Repository() as DataSource }
    bean { NavigationUtils }
}