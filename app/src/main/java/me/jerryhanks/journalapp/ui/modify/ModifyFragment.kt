package me.jerryhanks.journalapp.ui.modify


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.jerryhanks.journalapp.R

private const val EXTRA_DIARY_ID = "diary_id"

class ModifyFragment : Fragment() {
    private var diaryId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            diaryId = it.getLong(EXTRA_DIARY_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modify, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance(diaryId: String) =
                ModifyFragment().apply {
                    arguments = Bundle().apply {
                        putString(EXTRA_DIARY_ID, diaryId)
                    }
                }
    }
}
