package com.example.room.screens.addnote

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.room.APP
import com.example.room.R
import com.example.room.databinding.FragmentAddNoteBinding
import com.example.room.model.NoteModel

class AddNoteFragment : Fragment() {
    lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]

        binding.btnAddNote.setOnClickListener{
            val title = binding.etAddTitle.text.toString()
            val description = binding.etAddDesc.text.toString()

            viewModel.insert(NoteModel(title = title, description = description)){}
            APP.navController.navigate(R.id.action_addNoteFragment_to_startFragment)
        }

        binding.btnBack.setOnClickListener{
            APP.navController.navigate(R.id.action_addNoteFragment_to_startFragment)
        }

    }

}