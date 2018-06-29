package me.jerryhanks.journalapp.ui.entries

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.data.db.Diary


/**
 * @author Po10cio on 28/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */
class NotesAdapter(private val clickListener: NotesClickCallback) : PagedListAdapter<Diary, NotesAdapter.NotesViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotesViewHolder(rootView)

    }

    override fun onBindViewHolder(holder: NotesViewHolder, postion: Int) {
        //do nothing for now
        val note = getItem(postion)
        note?.let {
            holder.bindNote(it)
            holder.containerView.setOnClickListener {
                clickListener.onItemClicked(note)
            }
        }


    }

    interface NotesClickCallback {
        fun onItemClicked(diary: Diary)
    }

    inner class NotesViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindNote(item: Diary) {


        }

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Diary>() {
            override fun areItemsTheSame(oldItem: Diary, newItem: Diary): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Diary, newItem: Diary): Boolean {
                return oldItem == newItem
            }
        }

    }
}