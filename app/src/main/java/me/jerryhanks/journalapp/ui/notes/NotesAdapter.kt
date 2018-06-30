package me.jerryhanks.journalapp.ui.notes

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note.*
import me.jerryhanks.journalapp.R
import me.jerryhanks.journalapp.data.db.Note
import me.jerryhanks.journalapp.ui.utils.toFormattedString


/**
 * @author Po10cio on 28/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */
class NotesAdapter(private val clickListener: NotesClickCallback) : PagedListAdapter<Note, NotesAdapter.NotesViewHolder>(DIFF_CALLBACK) {
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
        fun onItemClicked(diary: Note)
    }

    inner class NotesViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindNote(note: Note) {
            val context = containerView.context
            tvNoteTitle.text = note.title
            tvNoteContent.text = note.content
            tvCreatedAt.text = context.getString(R.string.format_created_at, note.createdAt.toFormattedString())
            tvUpdatedAt.text = context.getString(R.string.format_updated_at, note.updateAt.toFormattedString())


        }

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }

    }
}