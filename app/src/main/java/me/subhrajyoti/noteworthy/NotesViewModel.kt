package me.subhrajyoti.noteworthy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.subhrajyoti.noteworthy.data.NoteModel
import me.subhrajyoti.noteworthy.data.NotesRepository

class NotesViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    val currentNote = MutableLiveData<NoteModel>()

    fun getAllNotes() = notesRepository.notes

    fun addNote(note: NoteModel) {
        notesRepository.addNote(note)
    }

    fun getNote(noteId: String) {
        val note = notesRepository.getNote(noteId)
        currentNote.postValue(note)
    }
}