package me.subhrajyoti.noteworthy.data

import java.util.*

data class NoteModel(
    val id: String = UUID.randomUUID().toString(),
    val dateOfCreation: Long,
    val title: String,
    val content: String
)