package com.example.mvvmtest;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {

    //androidviewmodel is subclass of view model,
    //advantage we have application variable, to get the context of our OS

    //view model is design to outlive its value and configuration even if activity is destroyed.
    //if it hold reference to already destroyed activity, we have a memory leak

    //this is where application variable becomes handy

    private NoteRepository repository;

    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        repository= new NoteRepository(application);

        allNotes = repository.getAllNotes();

    }

    //our activity later will have reference to view model, not the repository
    //this is where we create wrapper method for our
    //database operation method from our repository

    public void insert(Note note){
        repository.insert(note);
    }

    public void update(Note note){
        repository.update(note);
    }

    public void delete(Note note){
        repository.delete(note);
    }

    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
}
