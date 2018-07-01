package me.jerryhanks.journalapp

import me.jerryhanks.journalapp.data.NotesDaoTest
import me.jerryhanks.journalapp.ui.signin.SignInFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


/**
 * @author Po10cio on 27/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(
        ExampleInstrumentedTest::class,
        NotesDaoTest::class,
        SignInFragmentTest::class)
class InstrumentationTestSuite