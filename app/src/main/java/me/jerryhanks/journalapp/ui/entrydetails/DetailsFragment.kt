package me.jerryhanks.journalapp.ui.entrydetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_details.*

import me.jerryhanks.journalapp.R

private const val EXTRA_DIARY_ID = "diary_id"
private const val TAG = "DetailsFragment"

class DetailsFragment : Fragment() {

    private var diaryId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            diaryId = it.getString(EXTRA_DIARY_ID)
        }

        setHasOptionsMenu(true)
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
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_diary_deatails, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
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
        fun newInstance(diaryId: String) =
                DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString(EXTRA_DIARY_ID, diaryId)
                    }
                }
    }
}
