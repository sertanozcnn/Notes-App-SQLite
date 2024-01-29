package com.sertanozcnn.notessqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sertanozcnn.notessqlite.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {


    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDataBaseHelper
    private var noteID: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDataBaseHelper(this)
        noteID = intent.getIntExtra("note_id", -1)
        if (noteID == -1) {
            finish()
            return
        }

        val note = db.getNoteByID(noteID)
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updateButton.setOnClickListener {

            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updateNote = Note(noteID, newTitle, newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }

    }
}