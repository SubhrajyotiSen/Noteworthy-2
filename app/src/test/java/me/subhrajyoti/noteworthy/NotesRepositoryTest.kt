package me.subhrajyoti.noteworthy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import me.subhrajyoti.noteworthy.data.NoteModel
import me.subhrajyoti.noteworthy.data.NotesRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class NotesRepositoryTest {

    private lateinit var notesRepository: NotesRepository

    @get:Rule
    val rule = InstantTaskExecutorRule()

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

        Assert.assertEquals(notesRepository.notes.value?.size, 1)
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

    /**
     * Add a note to the NoteRepository and then bookmark the same note.
     * Verify that the note got added to bookmark.
     */
    @Test
    fun `verify note got bookmarked`() {
        val note = NoteModel(
            dateOfCreation = System.currentTimeMillis(),
            title = "Hakuna Matata",
            content = "It means no worries"
        )
        notesRepository.addNote(note)
        notesRepository.toggleBookmarkForNote(id = note.id)

        var fetchedNote = notesRepository.getNote(id = note.id)
        Assert.assertTrue(fetchedNote!!.isBookmarked)

        notesRepository.toggleBookmarkForNote(id = note.id)
        fetchedNote = notesRepository.getNote(id = note.id)
        Assert.assertFalse(fetchedNote!!.isBookmarked)
    }
}