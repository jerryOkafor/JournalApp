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
interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateDiary(diary: Diary)

    @Query("SELECT * FROM diaries WHERE id = :id")
    fun getDiaryById(id: Long): LiveData<Diary>

    @Query("SELECT * FROM diaries ORDER BY createdAt ASC")
    fun diariesByDate(): DataSource.Factory<Int, Diary>

    @Query("DELETE FROM diaries WHERE id = :noteId")
    fun deleteNoteById(noteId: Long): Int

}