package com.example.mvvmtest;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDao {

    // dao has to be interface or abstract

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    //room will automatically check note_table column,
    //compatible with list of note object we created.

    @Query("SELECT *FROM note_table ORDER BY priority DESC")
    List<Note> getAllNotes();

    //we can also do

    @Query("SELECT *FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotesLiveData();




}
