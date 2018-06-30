package me.jerryhanks.journalapp.ui.createorupdate

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import me.jerryhanks.journalapp.data.DataSource
import me.jerryhanks.journalapp.data.db.Note


/**
 * @author Po10cio on 28/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */
class CreateOrUpdateViewModel(private val dataSource: DataSource) : ViewModel() {

    private val noteId = MutableLiveData<Long>()

    private val note = Transformations.switchMap(noteId) {
        return@switchMap dataSource.getNoteById(it)
    }

    fun setNoteId(id: Long) {
        if (id == -1L) {
            return
        }
        noteId.postValue(id)
    }

    fun getNote(): LiveData<Note> {
        return note
    }

    fun createOrUpdateNote(diary: Note) {
        dataSource.createOrUpdateNote(diary)
    }


}