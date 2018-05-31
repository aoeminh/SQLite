package com.example.apple.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.myapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
private List<Note> notes = new ArrayList<Note>();
private Context context;
    //Contructor
    public NoteAdapter (List<Note> list , Context context){
        this.context = context;
        this.notes = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_note,parent,false);
        return  new ViewHolder(rowView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Note note = notes.get(position);
        holder.noteContent_tv.setText(note.getNote());
        holder.noteDate_tv.setText(note.getDate());
        holder.delete_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteSqlSource noteSqlSource  = new NoteSqlSource(context);
                noteSqlSource.delete(note);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView noteContent_tv;
        public TextView noteDate_tv;
        public ImageView delete_imv;
        public ViewHolder(View itemView) {
            super(itemView);

            noteContent_tv = (TextView) itemView.findViewById(R.id.tv_name_note);
            noteDate_tv = (TextView)itemView.findViewById(R.id.tv_date);
            delete_imv =(ImageView) itemView.findViewById(R.id.im_delete);



        }
    }
}
