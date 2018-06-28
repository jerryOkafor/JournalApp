package me.jerryhanks.journalapp.data

import android.arch.lifecycle.LiveData
import me.jerryhanks.journalapp.AppExecutors
import me.jerryhanks.journalapp.data.db.Diary
import me.jerryhanks.journalapp.data.db.JournalDb


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

class Repository(private val roomDb: JournalDb,
                 private val appExecutors: AppExecutors) : DataSource {
    override fun createOrUpdateNote(note: Diary) {
        appExecutors.diskIO().execute {
            roomDb.diaries().insertOrUpdateDiary(note)
        }
    }

    override fun getNoteById(noteId: Long): LiveData<Diary> {
        return roomDb.diaries().getDiaryById(noteId)
    }

    override fun getAllNotes(): android.arch.paging.DataSource.Factory<Int, Diary> {
        return roomDb.diaries().diariesByDate()
    }

}