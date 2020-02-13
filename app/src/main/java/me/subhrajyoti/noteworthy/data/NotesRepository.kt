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

    /**
     * Toggles bookmark state for given note id
     *
     * If bookmarked, remove from bookmark and vice versa
     */
    fun toggleBookmarkForNote(id: String) {
        var index = -1
        for (i in notesList.indices) {
            if (notesList[i].id == id) {
                index = i
                break
            }
        }
        if (index != -1) {
            val note = notesList[index]
            notesList[index].isBookmarked = !note.isBookmarked
            notes.postValue(notesList)
        }
    }
}