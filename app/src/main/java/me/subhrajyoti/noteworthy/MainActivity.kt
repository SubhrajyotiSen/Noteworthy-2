package me.subhrajyoti.noteworthy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val allNotesFragment: AllNotesFragment
        if (savedInstanceState == null || supportFragmentManager.findFragmentByTag(AllNotesFragment.TAG) == null) {
            allNotesFragment = AllNotesFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, allNotesFragment, AllNotesFragment.TAG)
            transaction.commit()
        }

    }
}
