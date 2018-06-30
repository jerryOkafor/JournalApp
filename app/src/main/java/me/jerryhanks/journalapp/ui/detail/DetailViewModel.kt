package me.jerryhanks.journalapp.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import me.jerryhanks.journalapp.data.DataSource
import me.jerryhanks.journalapp.data.db.Note
import me.jerryhanks.journalapp.di.OpenForTesting
import me.jerryhanks.journalapp.utils.NullLiveData


/**
 * @author Po10cio on 29/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@OpenForTesting
class DetailViewModel(private val dataSource: DataSource) : ViewModel() {
    private val noteId = MutableLiveData<Long>()
    private val error = MutableLiveData<String>()

    private val note = Transformations.switchMap(noteId) {
        return@switchMap if (it == null) {
            NullLiveData.create()
        } else {
            dataSource.getNoteById(it)
        }
    }

    fun setNoteId(id: Long?) {
        if (id == null) {
            error.postValue("Journal note not found.")
        }

        noteId.postValue(id)

    }

    fun getNote(): LiveData<Note> {
        return note
    }

    fun getError(): LiveData<String> {
        return error
    }

    fun deleteNote(noteId: Long): LiveData<Int> {
        return dataSource.deleteNoteById(noteId)
    }

}