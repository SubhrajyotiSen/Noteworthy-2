package me.subhrajyoti.noteworthy.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_view_note.*
import me.subhrajyoti.noteworthy.NotesApp
import me.subhrajyoti.noteworthy.NotesViewModel
import me.subhrajyoti.noteworthy.R
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
            requireActivity(),
            (requireActivity().applicationContext as NotesApp).viewModelFactory
        ).get(NotesViewModel::class.java)

        if (arguments != null && requireArguments().containsKey(NOTE_ID)) {
            val noteId = requireArguments().getString(NOTE_ID)!!

            notesViewModel.getNote(noteId)
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // this prevents ViewNoteFragment from overlapping on top of AllNotesFragment
                parentFragmentManager.popBackStack(
                    parentFragmentManager.getBackStackEntryAt(0).id,
                    POP_BACK_STACK_INCLUSIVE
                )
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
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