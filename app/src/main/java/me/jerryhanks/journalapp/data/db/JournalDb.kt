package me.jerryhanks.journalapp.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@Database(entities = [(Note::class)], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class JournalDb : RoomDatabase() {

    abstract fun diaries(): NotesDao

    companion object {
        fun create(context: Context, usInMemory: Boolean): JournalDb {
            val dbBuilder = if (usInMemory) {
                Room.inMemoryDatabaseBuilder(context, JournalDb::class.java)
                        .allowMainThreadQueries()
            } else {
                Room.databaseBuilder(context, JournalDb::class.java, "journalDb")
            }

            return dbBuilder.fallbackToDestructiveMigration()
                    .build()
        }
    }

}