package me.jerryhanks.journalapp.data

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import me.jerryhanks.journalapp.data.db.Diary


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

interface DataSource {

    fun createOrUpdateNote(note: Diary)

    fun getNoteById(noteId: Long): LiveData<Diary>

    fun getAllNotes(): DataSource.Factory<Int, Diary>

    fun deleteNoteById(noteId: Long): LiveData<Int>

}