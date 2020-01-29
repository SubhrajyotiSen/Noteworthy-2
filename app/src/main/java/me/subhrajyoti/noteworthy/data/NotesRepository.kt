package me.subhrajyoti.noteworthy.data

import androidx.lifecycle.MutableLiveData

class NotesRepository {

    private val notesList = mutableListOf<NoteModel>()
    val notes = MutableLiveData(notesList)

    fun addNote(noteModel: NoteModel) {
        notesList.add(noteModel)
        notes.postValue(notesList)
    }

    fun getNote(id: String): NoteModel? {
        return notesList.find {
            it.id == id
        }
    }
}