package me.subhrajyoti.noteworthy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var viewModelFactory: NotesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelFactory = NotesViewModelFactory(this)

        val allNotesFragment = AllNotesFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, allNotesFragment)
        transaction.commit()
    }
}
