package com.example.room.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.databinding.FragmentDetailBinding
import com.example.room.model.NoteModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        @Suppress("DEPRECATION") val currentNote: NoteModel = arguments?.getSerializable("note") as NoteModel

        binding.tvTitleDetail.text = currentNote.title
        binding.tvDescDetail.text = currentNote.description

        binding.btnDelete.setOnClickListener{
            viewModel.delete(currentNote){
                requireActivity().runOnUiThread {
                    findNavController().navigate(R.id.action_detailFragment_to_startFragment)
                }
            }
        }

        binding.btnBack.setOnClickListener{
            findNavController().navigate(R.id.action_detailFragment_to_startFragment)
        }

    }

}