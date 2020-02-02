package me.subhrajyoti.noteworthy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.subhrajyoti.noteworthy.data.NotesRepository

class NotesViewModelFactory (
    private val notesRepository: NotesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesViewModel(notesRepository) as T
    }
}