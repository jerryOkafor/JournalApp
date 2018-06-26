package me.jerryhanks.journalapp

import android.app.Application
import me.jerryhanks.journalapp.di.appModule
import org.koin.android.ext.android.startKoin


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        //start koin for di
        startKoin(this, listOf(appModule))
    }

}