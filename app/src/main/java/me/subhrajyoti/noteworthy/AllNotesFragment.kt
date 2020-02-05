package me.subhrajyoti.noteworthy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_all_notes.*

class AllNotesFragment : Fragment(R.layout.fragment_all_notes) {

    companion object {
        const val TAG = "AllNotesFragment"
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

        val notesAdapter = NotesAdapter { noteId ->
            openNote(noteId, savedInstanceState)
        }

        all_notes_recyclerView.adapter = notesAdapter

        notesViewModel.getAllNotes().observe(viewLifecycleOwner,
            Observer {
                notesAdapter.submitList(it)
            })

        add_note_fab.setOnClickListener {
            startAddNotesFragment(savedInstanceState)
        }
    }

    private fun openNote(noteId: String, savedInstanceState: Bundle?) {
        if (savedInstanceState == null || parentFragmentManager.findFragmentByTag(ViewNoteFragment.TAG) == null) {
            val viewNoteFragment = ViewNoteFragment()
            viewNoteFragment.arguments = Bundle().apply {
                putString(ViewNoteFragment.NOTE_ID, noteId)
            }
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, viewNoteFragment, ViewNoteFragment.TAG)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun startAddNotesFragment(savedInstanceState: Bundle?) {
        val addNoteFragment: AddNoteFragment
        if (savedInstanceState == null || parentFragmentManager.findFragmentByTag(AddNoteFragment.TAG) == null) {
            addNoteFragment  = AddNoteFragment()
            addNoteFragment.arguments = Bundle().apply {
                putFloat(CircularRevealFragment.START_X, (add_note_fab.x + add_note_fab.width / 2))
                putFloat(CircularRevealFragment.START_Y, (add_note_fab.y + add_note_fab.height / 2))
            }
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, addNoteFragment, AddNoteFragment.TAG)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}