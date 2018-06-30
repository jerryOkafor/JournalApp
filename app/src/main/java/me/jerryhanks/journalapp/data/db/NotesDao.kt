package me.jerryhanks.journalapp.data.db

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.*


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateDiary(diary: Note)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getDiaryById(id: Long): LiveData<Note>

    @Query("SELECT * FROM notes ORDER BY createdAt ASC")
    fun notesByDate(): DataSource.Factory<Int, Note>

    @Query("DELETE FROM notes WHERE id = :noteId")
    fun deleteNoteById(noteId: Long): Int

}