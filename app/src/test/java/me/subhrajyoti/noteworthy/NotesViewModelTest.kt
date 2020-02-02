package me.subhrajyoti.noteworthy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import me.subhrajyoti.noteworthy.data.NoteModel
import me.subhrajyoti.noteworthy.data.NotesRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotesViewModelTest {

    private lateinit var notesRepository: NotesRepository
    private lateinit var notesViewModel: NotesViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun init() {
        notesRepository = NotesRepository()
        notesViewModel = NotesViewModel(notesRepository)
    }

    @Test
    fun `get all notes`() {
        val note = NoteModel(
            dateOfCreation = System.currentTimeMillis(),
            title = "Hakuna Matata",
            content = "It means no worries"
        )
        notesRepository.addNote(note)

        Assert.assertEquals(notesViewModel.getAllNotes().value?.size, 1)
    }

    @Test
    fun `add note`() {
        val note = NoteModel(
            dateOfCreation = System.currentTimeMillis(),
            title = "Hakuna Matata",
            content = "It means no worries"
        )
        notesViewModel.addNote(note)

        val fetchedNote = notesRepository.getNote(note.id)

        Assert.assertEquals(note, fetchedNote)
    }

    @Test
    fun `get note by id`() {
        val note = NoteModel(
            dateOfCreation = System.currentTimeMillis(),
            title = "Hakuna Matata",
            content = "It means no worries"
        )

        notesViewModel.addNote(note)

        notesViewModel.getNote(note.id)

        Assert.assertEquals(note, notesViewModel.currentNote.value)
    }

}