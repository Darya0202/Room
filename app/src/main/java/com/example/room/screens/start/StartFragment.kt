package com.example.room.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.REPOSITORY
import com.example.room.adapter.NoteAdapter
import com.example.room.databinding.FragmentStartBinding
import com.example.room.db.NoteDatabase
import com.example.room.db.repository.NoteRepositoryImpl

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[StartViewModel::class.java]

        val daoNote = context?.let { NoteDatabase.getInstance(it).getNoteDao() }
        REPOSITORY = daoNote?.let { NoteRepositoryImpl(it) }!!

        val recyclerView = binding.rvNotes
        val adapter = NoteAdapter{ note ->
            val bundle = Bundle().apply {
                putSerializable("note", note)
            }
            findNavController().navigate(R.id.action_startFragment_to_detailFragment, bundle)
        }
        recyclerView.adapter = adapter

        viewModel.getAllNotes().observe(viewLifecycleOwner) { ListNotes ->
            adapter.setList(ListNotes.asReversed())
        }

        binding.btnNext.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_addNoteFragment)
        }

    }

}