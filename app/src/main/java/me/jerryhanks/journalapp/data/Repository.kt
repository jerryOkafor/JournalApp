package me.jerryhanks.journalapp.data

import android.arch.lifecycle.LiveData
import me.jerryhanks.journalapp.AppExecutors
import me.jerryhanks.journalapp.data.db.Note
import me.jerryhanks.journalapp.data.db.JournalDb
import me.jerryhanks.journalapp.di.OpenForTesting


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@OpenForTesting
class Repository(val roomDb: JournalDb,
                 val appExecutors: AppExecutors) : DataSource {
    override fun createOrUpdateNote(note: Note) {
        appExecutors.diskIO().execute {
            roomDb.notesDao().insertOrUpdateNote(note)
        }
    }

    override fun getNoteById(noteId: Long): LiveData<Note> {
        return roomDb.notesDao().getNoteById(noteId)
    }

    override fun getAllNotes(): android.arch.paging.DataSource.Factory<Int, Note> {
        return roomDb.notesDao().notes()
    }

    override fun deleteNoteById(noteId: Long): LiveData<Int> {
        return object : LiveData<Int>() {
            override fun onActive() {
                super.onActive()
                appExecutors.diskIO().execute {
                    val affectedRows = roomDb.notesDao().deleteNoteById(noteId)
                    postValue(affectedRows)
                }
            }
        }

    }


}