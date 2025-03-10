package com.example.room.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.room.REPOSITORY
import com.example.room.db.NoteDatabase
import com.example.room.db.repository.NoteRepositoryImpl
import com.example.room.model.NoteModel


class StartViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    fun initDataBase(){
        val daoNote = NoteDatabase.getInstance(context).getNoteDao()
        REPOSITORY = NoteRepositoryImpl(daoNote)
    }

    fun getAllNotes(): LiveData<List<NoteModel>>{
        return REPOSITORY.allNotes
    }
}