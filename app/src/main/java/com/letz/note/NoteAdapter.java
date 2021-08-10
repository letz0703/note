package com.letz.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder>
{
    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position)
    {
        Note currentNote = notes.get(position);
        holder.textViewNote.setText(currentNote.getDescription());
    }

    @Override
    public int getItemCount()
    {
        return notes .size();
    }

    public void setNotes(List<Note> notes)
    {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNotes(int position)
    {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder
    {
        TextView textViewNote;

        public NoteHolder(@NonNull View itemView)
        {
            super(itemView);
            textViewNote = itemView.findViewById(R.id.textViewNote);
        }
    }
}
