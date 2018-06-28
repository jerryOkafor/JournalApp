package me.jerryhanks.journalapp.ui.entries


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_entires.*
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.ui.utils.NavigationUtils
import org.koin.android.ext.android.inject

class EntriesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private val googleSignInClient: GoogleSignInClient by inject()
    private val navUtil: NavigationUtils by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entires, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appCompatActivity = requireActivity() as AppCompatActivity
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        button.setOnClickListener {
//            navUtil.gotoDetails(requireActivity())
//        }
        fabCreateDiary.setOnClickListener {
            //create a new Diary item
            navUtil.gotoCreateOrUpdate(requireActivity(), -1)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_entries, menu)

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item?.itemId
        return when (itemId) {
            android.R.id.home -> {
                requireActivity().supportFragmentManager.popBackStackImmediate()
                true
            }
            R.id.action_logout -> {

                //firebase signOut
                FirebaseAuth.getInstance().signOut()

                //Todo : show a progress for a longer ui operation

                //google signIn >> signOut
                googleSignInClient.signOut().addOnCompleteListener(requireActivity()) {
                    if (it.exception != null) {
                        Log.w(TAG, "Google SignOut failed", it.exception)
                        Snackbar.make(rootView, "Unable to complete signOut: ${it.exception.toString()}", Snackbar.LENGTH_LONG)
                                .show()
                        return@addOnCompleteListener
                    }

                    Toast.makeText(requireContext(), "SignOut successful", Toast.LENGTH_LONG).show()

                    //take the user to signIn
                    navUtil.gotoSignIn(requireActivity())

                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    companion object {
        private const val TAG = "EntriesFragment"
    }

}
