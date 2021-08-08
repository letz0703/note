package com.letz.note;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity
{

    EditText memo;
    Button cancel;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Note");
        setContentView(R.layout.activity_add_note);

        memo = findViewById(R.id.memo);
        cancel = findViewById(R.id.btn_cancel_activity_add_note);
        save = findViewById(R.id.btn_save_activity_add_note);

        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddNoteActivity.this, "nothing saved", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    public void saveNote() {
        Intent i =  new Intent();
        String userNote = memo.getText().toString();
        i.putExtra("note",userNote);
        setResult(Activity.RESULT_OK,i);
        finish();
    }
}