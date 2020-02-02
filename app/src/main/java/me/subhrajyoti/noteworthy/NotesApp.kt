package me.subhrajyoti.noteworthy

import android.app.Application
import me.subhrajyoti.noteworthy.data.NotesRepository

class NotesApp : Application() {

    lateinit var viewModelFactory: NotesViewModelFactory

    override fun onCreate() {
        super.onCreate()
        val notesRepository = NotesRepository()
        viewModelFactory = NotesViewModelFactory(notesRepository)
    }
}