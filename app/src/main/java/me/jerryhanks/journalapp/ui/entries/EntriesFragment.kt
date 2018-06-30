package me.jerryhanks.journalapp.ui.entries


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_entires.*
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.data.db.Note
import me.jerryhanks.journalapp.ui.utils.NavigationUtils
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class EntriesFragment : Fragment() {

    private val googleSignInClient: GoogleSignInClient by inject()
    private val navUtil: NavigationUtils by inject()
    private lateinit var notesAdapter: NotesAdapter
    private val notesItemClick = object : NotesAdapter.NotesClickCallback {
        override fun onItemClicked(diary: Note) {
            Log.d(TAG, "Item selected: $diary")
            navUtil.gotoDetails(requireActivity(), diary.id)
        }
    }

    private val entriesViewModel by viewModel<EntriesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        entriesViewModel.getNotes().observe(this, Observer {
            showEmptyView(it?.size == 0)
            notesAdapter.submitList(it)
        })
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

        fabCreateDiary.setOnClickListener {
            //getInstance a new note
            navUtil.gotoCreateOrUpdate(requireActivity(), 0L)
        }

        //init adapter
        notesAdapter = NotesAdapter(notesItemClick)
        notesAdapter.setHasStableIds(true)

        notesRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayout.VERTICAL, false)
        notesRecycler.setHasFixedSize(true)

        notesRecycler.adapter = notesAdapter
    }

    private fun showEmptyView(show: Boolean) {
        if (show) {
            emptyView.visibility = View.VISIBLE
            notesRecycler.visibility = View.GONE
        } else {
            emptyView.visibility = View.GONE
            notesRecycler.visibility = View.VISIBLE
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
