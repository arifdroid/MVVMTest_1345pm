package com.example.mvvmtest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //we cannot create new NoteViewModel
        //instead we let android decides

        //noteViewModel = ViewModelProviders.getClass(NoteViewModel.class);

        noteViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(MainActivity.this.getApplication()).getClass(NoteViewModel.class);
        //just checking

        //android system will destroy this reference when this activity is finish


        //below is live data method
        //observe is live data method,
        //will only update activity when it is on the foreground,
        //when activity destroyed, will delete all reference so that avoid memory leak
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update our recycler view later
                //onChange will only triggered when data or live data changes.
                Toast.makeText(MainActivity.this,"on changed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
