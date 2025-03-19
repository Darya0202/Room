package com.example.room.screens.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.databinding.FragmentAddNoteBinding
import com.example.room.model.NoteModel

class AddNoteFragment : Fragment() {
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]

        binding.btnAddNote.setOnClickListener{
            val title = binding.etAddTitle.text.toString()
            val description = binding.etAddDesc.text.toString()

            viewModel.insert(NoteModel(title = title, description = description)){
                requireActivity().runOnUiThread {
                    findNavController().navigate(R.id.action_addNoteFragment_to_startFragment)
                }
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_addNoteFragment_to_startFragment)
        }

    }

}