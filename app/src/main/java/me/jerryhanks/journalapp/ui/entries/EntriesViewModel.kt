package me.jerryhanks.journalapp.ui.entries

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import me.jerryhanks.journalapp.data.DataSource
import me.jerryhanks.journalapp.data.db.Note


/**
 * @author Po10cio on 28/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

class EntriesViewModel(dataSource: DataSource) : ViewModel() {
    private val notes: LiveData<PagedList<Note>> = LivePagedListBuilder(dataSource.getAllNotes(),
            PagedList.Config.Builder()
                    .setPageSize(DB_PAGE_SIZE)
                    .setInitialLoadSizeHint(DB_INITIAL_LOAD_SIZE)
                    .setEnablePlaceholders(true)
                    .setPrefetchDistance(PAGE_PRE_FETCH)
                    .build())
            .build()

    fun getNotes(): LiveData<PagedList<Note>> {
        return notes
    }

    companion object {
        const val DB_PAGE_SIZE = 20
        const val DB_INITIAL_LOAD_SIZE = 8
        const val PAGE_PRE_FETCH = 5
    }

}