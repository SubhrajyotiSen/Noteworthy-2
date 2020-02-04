package me.subhrajyoti.noteworthy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_view_note.*
import me.subhrajyoti.noteworthy.utils.DateUtil

class ViewNoteFragment : Fragment(R.layout.fragment_view_note) {

    companion object {
        const val NOTE_ID = "note_id"
        const val TAG = "ViewNoteFragment"
    }

    lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postponeEnterTransition()

        notesViewModel = ViewModelProvider(
            this,
            (requireActivity().applicationContext as NotesApp).viewModelFactory
        ).get(NotesViewModel::class.java)

        if (arguments != null && requireArguments().containsKey(NOTE_ID)) {
            val noteId = requireArguments().getString(NOTE_ID)!!

            notesViewModel.getNote(noteId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        notesViewModel.currentNote.observe(viewLifecycleOwner, Observer { note ->
            if (note != null) {
                note_title_textView.text = note.title
                note_content_textView.text = note.content
                note_creation_date_textView.text =
                    DateUtil.convertTimestampToReadableDateTime(note.dateOfCreation)
            }
        })
    }
}