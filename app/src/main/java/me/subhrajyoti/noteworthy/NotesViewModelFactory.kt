package me.subhrajyoti.noteworthy

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NotesViewModelFactory (
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val notesRepository = (context.applicationContext as NotesApp).notesRepository
        return NotesViewModel(notesRepository) as T
    }
}