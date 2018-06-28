package me.jerryhanks.journalapp.ui.modify


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_modify.*

import me.jerryhanks.journalapp.R

private const val EXTRA_DIARY_ID = "diary_id"
private const val TAG = "CreateOrUpdate"

class ModifyFragment : Fragment() {
    private var diaryId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            diaryId = it.getLong(EXTRA_DIARY_ID)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modify, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appCompactActivity = requireActivity() as AppCompatActivity
        appCompactActivity.setSupportActionBar(toolbar)
        appCompactActivity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_close)
            title = getString(R.string.creat_or_add_dairy_title)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.menu_modify_diary, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_create_or_update -> {
                createOrUpdateDiary()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun createOrUpdateDiary() {
        Log.d(TAG, "Inserting or updating diary")

    }


    companion object {
        @JvmStatic
        fun newInstance(diaryId: Long) =
                ModifyFragment().apply {
                    arguments = Bundle().apply {
                        putLong(EXTRA_DIARY_ID, diaryId)
                    }
                }
    }
}
