package com.letz.note;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_main);
        //리사이클 뷰 정의
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        // new LinearLayoutManager는 data가 top to bottom으로 보이게 한다.

        //NoteAdapter클래스 obj 생성
        NoteAdapter adapter = new NoteAdapter();
        //RecyclerView에 노트업답터 부착
        rv.setAdapter(adapter);

        noteViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>()
        {
            @Override
            public void onChanged(List<Note> notes)
            {
                // Update Recycler View
                adapter.setNotes(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                noteViewModel.delete(adapter.getNotes(viewHolder.getAdapterPosition()));
            }}).attachToRecyclerView(rv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.new_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.top_menu:
                gotoAddMemo();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void gotoAddMemo() {
        Intent intent = new Intent(this, AddNoteActivity.class);
//                startActivityForResult(intent, 1);
                startARLauncher.launch(intent);
//        startActivity(intent);
    }

    ActivityResultLauncher< Intent > startARLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();

                        Note note = new Note(data.getStringExtra("note"));
                        //Add this data to the DB
                        noteViewModel.insert(note);
                    }
                }
            }
    );

}