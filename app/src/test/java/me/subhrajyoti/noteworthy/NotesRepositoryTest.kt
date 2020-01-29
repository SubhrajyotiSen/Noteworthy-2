package me.subhrajyoti.noteworthy

import me.subhrajyoti.noteworthy.data.NoteModel
import me.subhrajyoti.noteworthy.data.NotesRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class NotesRepositoryTest {

    private lateinit var notesRepository: NotesRepository

    @Before
    fun init() {
        notesRepository = NotesRepository()
    }

    @Test
    fun `note added successfully`() {
        val note = NoteModel(
            dateOfCreation = System.currentTimeMillis(),
            title = "Hakuna Matata",
            content = "It means no worries"
        )
        notesRepository.addNote(note)

        Assert.assertEquals(notesRepository.getNotesCount(), 1)
    }

    @Test
    fun `note fetched successfully`() {
        val note = NoteModel(
            dateOfCreation = System.currentTimeMillis(),
            title = "Hakuna Matata",
            content = "It means no worries"
        )
        notesRepository.addNote(note)

        val fetchedNote = notesRepository.getNote(note.id)

        Assert.assertNotNull(fetchedNote)
        Assert.assertEquals(note, fetchedNote)
    }

    /** Searching for a note that does not exist in the
     * repository should return null
     */
    @Test
    fun `note not found`() {
        val note = NoteModel(
            dateOfCreation = System.currentTimeMillis(),
            title = "Hakuna Matata",
            content = "It means no worries"
        )
        notesRepository.addNote(note)

        val fetchedNote = notesRepository.getNote(UUID.randomUUID().toString())

        Assert.assertNull(fetchedNote)
    }
}