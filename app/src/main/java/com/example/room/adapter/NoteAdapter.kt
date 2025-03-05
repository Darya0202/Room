package com.example.room.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.model.NoteModel

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder> () {

     var listNote = emptyList<NoteModel>()

    class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = listNote[position]
        val titleView = holder.itemView.findViewById<TextView>(R.id.item_title)
        titleView.text = note.title
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<NoteModel>){
        listNote = list
        notifyDataSetChanged()
    }
}