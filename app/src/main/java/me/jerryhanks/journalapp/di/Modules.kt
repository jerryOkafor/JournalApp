package me.jerryhanks.journalapp.di

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.data.DataSource
import me.jerryhanks.journalapp.data.Repository
import me.jerryhanks.journalapp.ui.utils.NavigationUtils
import org.koin.android.ext.koin.androidApplication
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
    bean {
        GoogleSignIn.getClient(androidApplication(),
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(androidApplication().getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build())
    }
}