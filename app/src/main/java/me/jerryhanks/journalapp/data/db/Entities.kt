package me.jerryhanks.journalapp.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import java.util.*


/**
 * @author Po10cio on 26/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */
@Entity(tableName = "diaries", indices = [(Index(value = ["id"], unique = true))])
@TypeConverters(DateConverter::class)
data class Diary(
        @PrimaryKey
        val id: Long,
        val title: String,
        val content: String,
        val data: Date)