package me.subhrajyoti.noteworthy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.subhrajyoti.noteworthy.data.NoteModel
import me.subhrajyoti.noteworthy.utils.DateUtil

private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<NoteModel> () {
    override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem == newItem
    }
}

class NotesAdapter : ListAdapter<NoteModel, NotesAdapter.NoteViewHolder>(DIFF_CALLBACK) {


    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noteTitleTextView: TextView = view.findViewById(R.id.note_title_textView)
        val noteContentTextView: TextView = view.findViewById(R.id.note_content_textView)
        val noteCreationDateTextView: TextView = view.findViewById(R.id.creation_date_textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_viewholder, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        with(holder) {
            noteTitleTextView.text = note.title
            noteContentTextView.text = note.content
            noteCreationDateTextView.text = DateUtil.convertTimestampToReadableDateTime(note.dateOfCreation)
        }
    }
}