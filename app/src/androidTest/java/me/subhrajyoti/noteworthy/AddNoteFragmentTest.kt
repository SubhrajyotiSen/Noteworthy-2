package me.subhrajyoti.noteworthy

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddNoteFragmentTest {

    @Test
    fun testErrorMessageOnEmptyNoteTitle() {
        launchFragmentInContainer<AddNoteFragment>(themeResId = R.style.AppTheme)
        onView(withId(R.id.note_title_editText)).perform(typeText(""))
        onView(withId(R.id.save_note_fab)).perform(click())
        onView(withId(R.id.note_title_textInputLayout)).check(matches(hasTextInputLayoutErrorText("Note title cannot be empty")))
    }

    private fun hasTextInputLayoutErrorText(expectedErrorText: String): Matcher<View> = object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description?) { }

        override fun matchesSafely(item: View?): Boolean {
            if (item !is TextInputLayout) return false
            val error = item.error ?: return false
            val hint = error.toString()
            return expectedErrorText == hint
        }
    }
}