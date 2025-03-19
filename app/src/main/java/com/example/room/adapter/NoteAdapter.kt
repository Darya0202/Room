package com.example.room.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemLayoutBinding
import com.example.room.model.NoteModel

class NoteAdapter(private val onNoteClick: (NoteModel) -> Unit): RecyclerView.Adapter<NoteAdapter.NoteViewHolder> () {

    var listNote = emptyList<NoteModel>()

    class NoteViewHolder(private val viewBinding: ItemLayoutBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(note: NoteModel) {
            viewBinding.itemTitle.text = note.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note: NoteModel = listNote[position]

        holder.bind(note)

        holder.itemView.setOnClickListener {
            onNoteClick(note)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteModel>){
        listNote = list
        notifyDataSetChanged()
    }
}
