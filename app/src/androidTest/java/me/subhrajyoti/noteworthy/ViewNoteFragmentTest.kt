package me.subhrajyoti.noteworthy

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import me.subhrajyoti.noteworthy.data.NoteModel
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewNoteFragmentTest {

    @Test
    fun testIfNoteDetailsAreDisplayedCorrectly() {
        val currentTime = System.currentTimeMillis()
        val note = NoteModel(
            title = "New note",
            content = "I can't think of any text",
            dateOfCreation = currentTime
        )

        with(launchFragmentInContainer<ViewNoteFragment>(themeResId = R.style.AppTheme)) {
            onFragment { fragment ->
                fragment.notesViewModel.currentNote.postValue(note)
            }
            Espresso.onView(ViewMatchers.withId(R.id.note_title_textView))
                .check(ViewAssertions.matches(ViewMatchers.withText(note.title)))

            Espresso.onView(ViewMatchers.withId(R.id.note_content_textView))
                .check(ViewAssertions.matches(ViewMatchers.withText(note.content)))
        }
    }
}