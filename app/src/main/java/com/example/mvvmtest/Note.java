package com.example.mvvmtest;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//so entity is the java class that will represent one table in our database


@Entity(tableName = "note_table")   //sqlite naming convention
public class Note {


        @PrimaryKey(autoGenerate = true) //means with every new note generated sqlite will automatically gives out IS
        private int id; //each sqlite need primary key., in here we defined as ID

        private String title;
        private String description;

      //  @ColumnInfo(name = "priority_nama_sengaja") //just to have other name
        private int priority;

        //id will be automatically generated

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    //use to initialize so that sqlite can automated it later on
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
