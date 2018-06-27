package me.jerryhanks.journalapp.data.db

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@Dao
interface DiaryDao {

    @Insert
    fun insertDiary(diary: Diary)

    @Update
    fun updateDiary(diary: Diary)

    @Query("SELECT * FROM diaries WHERE id = :id")
    fun getDiaryById(id: Long): LiveData<Diary>

    @Query("SELECT * FROM diaries ORDER BY date ASC")
    fun diariesByDate(): DataSource.Factory<Int, Diary>

}