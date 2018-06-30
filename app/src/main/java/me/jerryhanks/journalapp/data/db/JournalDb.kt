package me.jerryhanks.journalapp.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import me.jerryhanks.journalapp.R
import java.util.*
import java.util.concurrent.Executors


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@Database(entities = [(Note::class)], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class JournalDb : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        private const val APP_DB_NAME = "journalDb"
        @Volatile
        var INSTANCE: JournalDb? = null

        fun getInstance(context: Context, usInMemory: Boolean): JournalDb {
            val dbBuilder = if (usInMemory) {
                Room.inMemoryDatabaseBuilder(context, JournalDb::class.java)
                        .allowMainThreadQueries()
            } else {
                Room.databaseBuilder(context, JournalDb::class.java, APP_DB_NAME)
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                //prepopulate the room db here
                                Executors.newSingleThreadExecutor().execute {
                                    val sampleNote = Note(
                                            0,
                                            context.getString(R.string.sample_title),
                                            context.getString(R.string.sample_note_content),
                                            Date(),
                                            Date())

                                    getInstance(context, false).notesDao().insertOrUpdateNote(sampleNote)
                                }
                            }
                        })
            }
            return INSTANCE ?: synchronized(this) {
                dbBuilder.fallbackToDestructiveMigration()
                        .build()
                        .also {
                            INSTANCE = it
                        }
            }

        }
    }

}