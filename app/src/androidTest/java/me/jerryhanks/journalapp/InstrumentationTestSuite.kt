package me.jerryhanks.journalapp

import org.junit.runner.RunWith
import org.junit.runners.Suite


/**
 * @author Po10cio on 27/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(ExampleInstrumentedTest::class, NotesDaoTest::class)
class InstrumentationTestSuite