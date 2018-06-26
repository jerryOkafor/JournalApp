package me.jerryhanks.journalapp.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@Dao
interface DiaryDao {

    @Insert
    fun insertDiary(diary: Diary)

//    @Query("SELECT * FROM diaries ORDER BY date ASC")
//    fun diariesByDate(): DataSorce.Factory<Int, Diary>()

}