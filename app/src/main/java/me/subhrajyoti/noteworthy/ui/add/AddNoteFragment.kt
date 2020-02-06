package me.subhrajyoti.noteworthy.ui.add

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_add_note.*
import me.subhrajyoti.noteworthy.NotesApp
import me.subhrajyoti.noteworthy.NotesViewModel
import me.subhrajyoti.noteworthy.R
import me.subhrajyoti.noteworthy.data.NoteModel
import me.subhrajyoti.noteworthy.ui.CircularRevealFragment
import me.subhrajyoti.noteworthy.utils.NoteValidator


class AddNoteFragment : CircularRevealFragment(R.layout.fragment_add_note) {

    companion object {
        const val TAG = "AddNoteFragment"
    }

    private lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notesViewModel = ViewModelProvider(
            requireActivity(),
            (requireActivity().applicationContext as NotesApp).viewModelFactory
        ).get(NotesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        note_title_editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                val noteTitle = note_title_editText.text?.toString()
                validateNoteTitle(noteTitle).not()
            } else {
                true
            }
        }

        save_note_fab.setOnClickListener {
            val noteTitle = note_title_editText.text?.toString()
            val noteContent = note_content_editText.text?.toString()

            if (validateNoteTitle(noteTitle) && validateNoteContent(noteContent)) {
                val note = NoteModel(
                    dateOfCreation = System.currentTimeMillis(),
                    title = noteTitle!!,
                    content = noteContent!!
                )
                notesViewModel.addNote(note)
                requireActivity().onBackPressed()
            }
        }
    }

    private fun validateNoteTitle(noteTitle: String?): Boolean {
        if (NoteValidator.isValidText(noteTitle)) {
            note_title_textInputLayout.isErrorEnabled = false
            return true
        } else {
            note_title_textInputLayout.isErrorEnabled = true
            note_title_textInputLayout.error =
                resources.getString(R.string.empty_note_title_error_message)
            return false
        }
    }

    private fun validateNoteContent(noteContent: String?): Boolean {
        if (NoteValidator.isValidText(noteContent)) {
            note_content_textInputLayout.isErrorEnabled = false
            return true
        } else {
            note_content_textInputLayout.isErrorEnabled = true
            note_content_textInputLayout.error =
                resources.getString(R.string.empty_note_title_error_message)
            return false
        }
    }
}