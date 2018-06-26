package me.jerryhanks.journalapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.ui.utils.NavigationUtils
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navUtil: NavigationUtils by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //check if user is logged in or not
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            navUtil.gotoEntries(this)
        } else {
            navUtil.gotoSignIn(this)
        }

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}
