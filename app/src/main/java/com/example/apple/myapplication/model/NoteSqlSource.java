package com.example.apple.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteSqlSource {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String [] allColumn = {NoteSqliteHelper.COLUMN_ID,NoteSqliteHelper.COLUMN_NOTE,NoteSqliteHelper.COLUMN_DATE};
    private Context context;

    // intilize contructor to create database
    public NoteSqlSource (Context context){
        this.context = context;
        sqLiteOpenHelper = new NoteSqliteHelper(context);
    }

    // open connection to database
    public void open(){
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    // close connection to database
    public void close(){
        sqLiteOpenHelper.close();
    }

    //add new note
    public void addNote(String note){
        //get datetime
        String datetime = DateFormat.getDateTimeInstance().format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteSqliteHelper.COLUMN_NOTE,note);
        contentValues.put(NoteSqliteHelper.COLUMN_DATE, datetime);

        //insert
        sqLiteDatabase.insert(NoteSqliteHelper.TABLE_NAME,null,contentValues);
        Toast.makeText(this.context,"Add new note success",Toast.LENGTH_SHORT).show();
    }
    //delete
    public void delete(Note note){
        sqLiteDatabase.delete(NoteSqliteHelper.TABLE_NAME,NoteSqliteHelper.COLUMN_ID + "=" + note.getId(),null);
        Toast.makeText(this.context,"Delete success",Toast.LENGTH_SHORT).show();
    }

    public List<Note> getAllNote(){
        List<Note> notes = new ArrayList<Note>();
        Cursor cursor = sqLiteDatabase.query(NoteSqliteHelper.TABLE_NAME,allColumn
                ,null,null,null,null,null);

        //if null
        if(cursor == null){
            return null;
        }

        //if not null
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Note note  = cursorToModel(cursor);
            notes.add(note);
            cursor.moveToNext();
        }
        return notes;
    }

    public Note cursorToModel(Cursor cursor){
        Note note = new Note();
        note.setId(cursor.getInt(0));
        note.setNote(cursor.getString(1));
        note.setDate(cursor.getString(2));
        return note;
    }
}
