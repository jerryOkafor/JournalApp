package me.jerryhanks.journalapp.ui.entrydetails

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_details.*

import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.ui.utils.goBack
import me.jerryhanks.journalapp.ui.utils.toFormattedString
import org.koin.android.architecture.ext.viewModel

private const val EXTRA_DIARY_ID = "note_id"
private const val TAG = "DetailsFragment"

class DetailsFragment : Fragment() {

    private var noteId: Long? = null
    private val detailsViewModel by viewModel<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            noteId = it.getLong(EXTRA_DIARY_ID)
        }

        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailsViewModel.setNoteId(noteId)

        //start observing note
        detailsViewModel.getNote().observe(this, Observer {
            Log.d(TAG, "Note: $it")
            it?.let {
                tvNoteTitle.text = it.title
                tvNoteContent.text = it.content
                tvCreatedAt.text = getString(R.string.format_created_at, it.createdAt.toFormattedString())
                tvUpdatedAt.text = getString(R.string.format_updated_at, it.updateAt.toFormattedString())
            }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appCompatActivity = requireActivity() as AppCompatActivity
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.details_title)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_diary_deatails, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                goBack()
                true
            }
            R.id.action_modify -> {
                updateNote()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun updateNote() {
        Log.d(TAG, "Modifying Diary Item")

    }

    companion object {
        @JvmStatic
        fun newInstance(noteId: Long) =
                DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putLong(EXTRA_DIARY_ID, noteId)
                    }
                }
    }
}
