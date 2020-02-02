package me.subhrajyoti.noteworthy

import android.app.Application
import me.subhrajyoti.noteworthy.data.NotesRepository

class NotesApp : Application() {

    lateinit var notesRepository: NotesRepository

    override fun onCreate() {
        super.onCreate()
        notesRepository = NotesRepository()
    }
}