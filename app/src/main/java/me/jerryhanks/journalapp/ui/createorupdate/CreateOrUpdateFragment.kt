package me.jerryhanks.journalapp.ui.createorupdate


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_modify.*
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.data.db.Diary
import me.jerryhanks.journalapp.ui.utils.Util
import org.koin.android.architecture.ext.viewModel
import java.util.*

private const val EXTRA_DIARY_ID = "diary_id"
private const val TAG = "CreateOrUpdate"

class ModifyFragment : Fragment() {
    private var diaryId: Long = 0L
    private var note: Diary? = null

    private val viewModel by viewModel<CreateOrUpdateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            diaryId = it.getLong(EXTRA_DIARY_ID)
        }

        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setNoteId(diaryId)

        //observe the note if it exist before
        viewModel.getNote().observe(this, Observer {
            it?.let { item ->
                note = item
                editTextTitle.setText(item.title)
                editTextContent.setText(item.content)
            }

        })


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
            title = getString(R.string.create_or_add_dairy_title)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.menu_modify_diary, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                requireActivity().supportFragmentManager.popBackStack()
                true
            }
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

        val title = editTextTitle.text.toString()
        val content = editTextContent.text.toString()

        if (title.isEmpty()) {
            showMessage("Title can not be empty.")
            return
        }

        if (content.isEmpty()) {
            showMessage("Content can not be empty.")
            return
        }

        //if we are here, title and content are ot null
        //ns we are ready to go
        val newDiary = note?.copy(title = title, content = content, updateAt = Date())
                ?: Diary(diaryId, title, content, Date(), Date())

        //update or create the note
        viewModel.createOrUpdateNote(newDiary)
        showToastMessage(if (diaryId == 0L) "Note added successfully." else "Note Updated successfully")
        Util.closeSofteInputMethod(requireActivity())

        requireActivity().supportFragmentManager.popBackStack()

    }

    private fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun showMessage(error: String) {
        view?.let { Snackbar.make(it, error, Snackbar.LENGTH_LONG).show() }

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
