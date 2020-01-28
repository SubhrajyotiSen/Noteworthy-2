package me.subhrajyoti.noteworthy

import me.subhrajyoti.noteworthy.utils.NoteValidator
import org.junit.Assert
import org.junit.Test

class NoteValidatorText {

    @Test
    fun `check for null string`() {
        val text = null
        Assert.assertEquals(NoteValidator.isValidText(text), false)
    }

    @Test
    fun `check for empty string`() {
        val text = ""
        Assert.assertEquals(NoteValidator.isValidText(text), false)
    }

    @Test
    fun `check for string with just whitespaces`() {
        val text = "    "
        Assert.assertEquals(NoteValidator.isValidText(text), false)
    }

    @Test
    fun `check for valid text`() {
        val text = "This is a note"
        Assert.assertEquals(NoteValidator.isValidText(text), true)
    }

}