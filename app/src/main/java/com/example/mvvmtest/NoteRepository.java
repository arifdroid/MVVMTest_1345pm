package com.example.mvvmtest;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){

        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();

        allNotes=noteDao.getAllNotesLiveData();
    }

    ////////>>> DATABASE OPERATION METHOD

    public void insert(Note note){

        new DeleteNoteAsyncTask(noteDao).execute(note);

    }

    public void update(Note note){

        new UpdateNoteAsyncTask(noteDao).execute(note);

    }

    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes(){

        new DeleteAllNoteAsyncTask(noteDao).execute();

    }

    ///////////// .. UP IS database operation method

    //room will automatically execute the method return live data on our
    //background thread
    //but for other database operation, we have to manually specified. (insert, update, delete etc.)

    public LiveData<List<Note>> getAllNotes(){
        return  allNotes;
    }

    //it has to be static so it doesnt have a refence
    //to the repository itself
    //otherwise will cause memory leak
    private static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;
        //notedao is needed to make database operation

        //since class is static , we cannot access note dao directly so we have to create
        //constructor

        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            //return null;

            noteDao.insert(notes[0]);
            return null;

        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;
        //notedao is needed to make database operation

        //since class is static , we cannot access note dao directly so we have to create
        //constructor

        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            //return null;

            noteDao.update(notes[0]);
            return null;

        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;
        //notedao is needed to make database operation

        //since class is static , we cannot access note dao directly so we have to create
        //constructor

        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            //return null;

            noteDao.delete(notes[0]);
            return null;

        }
    }private static class DeleteAllNoteAsyncTask extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao;
        //notedao is needed to make database operation

        //since class is static , we cannot access note dao directly so we have to create
        //constructor

        private DeleteAllNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //return null;

            noteDao.deleteAllNotes();
            return null;

        }
    }
}
