package com.example.room.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.room.REPOSITORY
import com.example.room.model.NoteModel


class StartViewModel(private val application: Application): AndroidViewModel(application) {

    fun getAllNotes(): LiveData<List<NoteModel>>{
        return REPOSITORY.allNotes
    }
}