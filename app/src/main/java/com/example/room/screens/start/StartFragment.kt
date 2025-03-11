package com.example.room.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room.MainActivity
import com.example.room.R
import com.example.room.adapter.NoteAdapter
import com.example.room.databinding.FragmentStartBinding
import com.example.room.model.NoteModel

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[StartViewModel::class.java]
        viewModel.initDataBase()

        recyclerView = binding.rvNotes
        adapter = NoteAdapter(this)
        recyclerView.adapter = adapter

        viewModel.getAllNotes().observe(viewLifecycleOwner) { ListNotes ->
            adapter.setList(ListNotes.asReversed())
        }

        binding.btnNext.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_addNoteFragment)
        }
    }

    companion object{
        fun clickNote(fragment: Fragment, noteModel: NoteModel) {
            val bundle = Bundle()
            bundle.putSerializable("note", noteModel)
            fragment.findNavController().navigate(R.id.action_startFragment_to_detailFragment, bundle)
        }
    }

}