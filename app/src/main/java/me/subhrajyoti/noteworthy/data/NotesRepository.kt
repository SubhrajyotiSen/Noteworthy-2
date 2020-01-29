package me.subhrajyoti.noteworthy.data

class NotesRepository {

    private val notes = mutableListOf<NoteModel>()

    fun getAllNotes() = notes

    fun getNotesCount() = notes.size

    fun addNote(noteModel: NoteModel) {
        notes.add(noteModel)
    }

    fun getNote(id: String): NoteModel? {
        return notes.find {
            it.id == id
        }
    }
}