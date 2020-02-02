package me.subhrajyoti.noteworthy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_all_notes.*

class AllNotesFragment : Fragment(R.layout.fragment_all_notes) {

    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notesViewModel = ViewModelProvider(
            this,
            (requireActivity() as MainActivity).viewModelFactory
        ).get(NotesViewModel::class.java)

        notesAdapter = NotesAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        all_notes_recyclerView.adapter = notesAdapter

        notesViewModel.getAllNotes().observe(viewLifecycleOwner,
            Observer {
                notesAdapter.submitList(it)
            })

        add_note_fab.setOnClickListener {
            val addNoteFragment = AddNoteFragment()
            addNoteFragment.arguments = Bundle().apply {
                putFloat(CircularRevealFragment.START_X, (add_note_fab.x + add_note_fab.width / 2))
                putFloat(CircularRevealFragment.START_Y, (add_note_fab.y + add_note_fab.height / 2))
            }
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, addNoteFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}