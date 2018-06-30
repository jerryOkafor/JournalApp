/*
 * @author Po10cio on 30/06/2018
 * @mail jerryhanksokafor@gmail.com
 * @for JournalApp
 */
package me.jerryhanks.journalapp.utils

import org.mockito.ArgumentCaptor
import org.mockito.Mockito

/**
 * a kotlin friendly mock that handles generics
 */
inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

inline fun <reified T> argumentCaptor(): ArgumentCaptor<T> = ArgumentCaptor.forClass(T::class.java)