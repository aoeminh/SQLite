package com.example.apple.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apple.myapplication.R;
import com.example.apple.myapplication.model.Note;
import com.example.apple.myapplication.model.NoteAdapter;
import com.example.apple.myapplication.model.NoteSqlSource;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
private RecyclerView list_note_rv;
private EditText note_edt;
private Button add_btn;
private List<Note> notes = new ArrayList<Note>();
private NoteAdapter adapter;
    @Override
    int getId() {
        return R.layout.activity_main;
    }

    @Override
    void intiView() {
    list_note_rv = (RecyclerView) this.findViewById(R.id.rv_listnote);
    note_edt =(EditText) this.findViewById(R.id.edt_text);
    add_btn = (Button)this.findViewById(R.id.btn_add);

    NoteSqlSource noteSqlSource =new NoteSqlSource(this);
    noteSqlSource.open();
    if(noteSqlSource.getAllNote() !=null){
        notes = noteSqlSource.getAllNote();
        adapter = new NoteAdapter(notes,this);

        list_note_rv.setVisibility(View.VISIBLE);
    }
    }

    @Override
    void setAction() {
        add_btn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        NoteSqlSource noteSqlSource =new NoteSqlSource(this);

        noteSqlSource.addNote(note_edt.getText().toString());
        adapter.notifyDataSetChanged();
    }
}
