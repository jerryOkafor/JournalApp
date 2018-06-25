package me.jerryhanks.journalapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.ui.auth.AuthFragment
import me.jerryhanks.journalapp.ui.entries.EntriesFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //check if user is logged in or not
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            gotoEntries()
        } else {
            gotoSignIn()
        }

    }

    private fun gotoSignIn() {
        val signInFragment = AuthFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, signInFragment)
                .commitAllowingStateLoss()

    }

    private fun gotoEntries() {
        val entriesFragment = EntriesFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, entriesFragment)
                .addToBackStack("entries")
                .commitAllowingStateLoss()
    }

}
