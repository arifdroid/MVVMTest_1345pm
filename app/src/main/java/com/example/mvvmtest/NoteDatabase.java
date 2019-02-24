package com.example.mvvmtest;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


//version is whenever we make changes to ourdatabase, we have to increase version number,
//and provide migration stratgy.

@Database(entities = {Note.class},version = 1) //
public abstract class NoteDatabase extends RoomDatabase {

    //populate database when we created, instead of starting with zero notes.
    //create in onCreateMethod

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db); //we need to do these in the background, call asynctask
            new PopulateAsyncTask(instance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao;

        private PopulateAsyncTask(NoteDatabase db){

            this.noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.insert(new Note("Title 1","Description 1", 1));
            noteDao.insert(new Note("Title 2","Description 2", 2));
            noteDao.insert(new Note("Title 3","Description 3", 3));

            return null;
        }
    }



    //we create this static field or variable because we to create a singleton
    //singleton means we cant create multiple instances of this database
    //reuse same instance everywhere in our app



    private static NoteDatabase instance;



    public abstract NoteDao noteDao();
    //room will take care of our method

    //synchronized means only one thread at a time can access this method
    //this way we dont accidently create 2 instances of this database
    //when 2 different thread trying to access this method at the same time
    public static synchronized NoteDatabase getInstance(Context context){

        if(instance==null){
//            instance = Room.databaseBuilder(context.getApplicationContext(),
//                    Note.class,
//                    "note_database")
//                    .fallbackToDestructiveMigration()
//                    .build();

                instance = Room.databaseBuilder(context.getApplicationContext(),
                        NoteDatabase.class,
                        "note_database")
                        .fallbackToDestructiveMigration() //got something to do with version
                        .addCallback(roomCallback)
                        .build();
        }
        return instance;
    }

}
