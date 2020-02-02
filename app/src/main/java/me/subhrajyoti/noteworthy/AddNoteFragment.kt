package me.subhrajyoti.noteworthy

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_add_note.*
import me.subhrajyoti.noteworthy.data.NoteModel
import me.subhrajyoti.noteworthy.utils.NoteValidator


class AddNoteFragment : CircularRevealFragment(R.layout.fragment_add_note) {

    private lateinit var notesViewModel: NotesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesViewModel = ViewModelProvider(
            this,
            (requireActivity().applicationContext as NotesApp).viewModelFactory
        ).get(NotesViewModel::class.java)

        save_note_fab.setOnClickListener {
            val noteTitle = note_title_editText.text?.toString()
            if (NoteValidator.isValidText(noteTitle)) {
                note_title_textInputLayout.isErrorEnabled = false

                val noteContent = note_content_editText.text.toString()
                val note = NoteModel(
                    dateOfCreation = System.currentTimeMillis(),
                    title = noteTitle!!,
                    content = noteContent
                )
                notesViewModel.addNote(note)
                requireActivity().onBackPressed()
            } else {
                note_title_textInputLayout.isErrorEnabled = true
                note_title_textInputLayout.error =
                    resources.getString(R.string.empty_note_title_error_message)
            }
        }
    }
}