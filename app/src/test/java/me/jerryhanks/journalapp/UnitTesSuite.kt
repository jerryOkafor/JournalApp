package me.jerryhanks.journalapp

import me.jerryhanks.journalapp.createorupdate.CreateOrUpdateViewModelTest
import me.jerryhanks.journalapp.detatil.DetailViewModelTest
import me.jerryhanks.journalapp.notes.NotesViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


/**
 * @author Po10cio on 30/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(
        ExampleUnitTest::class,
        DetailViewModelTest::class,
        CreateOrUpdateViewModelTest::class,
        NotesViewModelTest::class)
class UnitTesSuite