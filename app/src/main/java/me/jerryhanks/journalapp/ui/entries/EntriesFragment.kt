package me.jerryhanks.journalapp.ui.entries


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_entires.*
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.ui.auth.AuthFragment
import me.jerryhanks.journalapp.ui.entrydetails.DetailsFragment

class EntriesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

        button.setOnClickListener {
            gotoDetails()
        }
    }

    private fun gotoDetails() {
        val appCompatActivity = requireActivity() as AppCompatActivity
        val supportFragmentManager = appCompatActivity.supportFragmentManager

        supportFragmentManager.popBackStackImmediate()

        val detailFragment = DetailsFragment.newInstance("")
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailFragment)
                .commitAllowingStateLoss()

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
                FirebaseAuth.getInstance().signOut()

                gotoSignIn()

                //Todo : signout from googleSignInClient too
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun gotoSignIn() {
        val appCompatActivity = requireActivity() as AppCompatActivity
        val supportFragmentManager = appCompatActivity.supportFragmentManager

        supportFragmentManager.popBackStackImmediate()

        val signInFragment = AuthFragment()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, signInFragment)
                .commitAllowingStateLoss()
    }


}
