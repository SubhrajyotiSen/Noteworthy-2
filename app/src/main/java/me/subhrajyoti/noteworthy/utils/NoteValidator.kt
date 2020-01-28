package me.subhrajyoti.noteworthy.utils

/***
 * The only condition for valid text is that it should be
 * not null and not empty.
 * Text with only whitespaces is also treated as invalid text
 */

object NoteValidator {

    fun isValidText(text: String?) = text.isNullOrBlank().not()

}