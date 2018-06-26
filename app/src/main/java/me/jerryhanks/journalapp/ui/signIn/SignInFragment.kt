package me.jerryhanks.journalapp.ui.signIn

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.fragment_sign_in.*
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.ui.entries.EntriesFragment
import org.koin.android.ext.android.inject


class SignInFragment : Fragment() {

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    private val googleSignInClient: GoogleSignInClient by inject()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInButton.setOnClickListener {
            startSignIn()
        }

    }

    private fun startSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RC_SIGN_IN -> {
                val signInTask = GoogleSignIn.getSignedInAccountFromIntent(data)

                try {
                    //check if Google sign in was successful, then auth with firebase
                    val account = signInTask.getResult(ApiException::class.java)

                    //auth with firebase
                    firebaseAuthWithGoogleAccount(account)

                } catch (e: Exception) {
                    Log.w(TAG, "Google Sign in failed", e)
                    Snackbar.make(rootView, "Authentication Failed.", Snackbar.LENGTH_LONG).show()
                }
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogle: ${account?.id}")

        //Todo show progress here

        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity()) {
                    if (it.isSuccessful) {
                        //sign in successful
                        Log.d(TAG, "signInWithCredentials: Successful")

                        Toast.makeText(requireContext(), "SignIn  Successful.", Toast.LENGTH_LONG).show()

                        //got to entries page
                        gotoEntries()

                    } else {
                        //sign in unSuccessful
                        Log.w(TAG, "signInWithCredentials: failure", it.exception)

                    }
                }

    }

    private fun gotoEntries() {
        val appCompactActivity = requireActivity() as AppCompatActivity
        val entriesFragment = EntriesFragment()
        val supportFragmentManager = appCompactActivity.supportFragmentManager

        supportFragmentManager.popBackStackImmediate()

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, entriesFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }


    companion object {
        const val RC_SIGN_IN = 1234
        const val TAG = "SignInFragment"
    }
}
