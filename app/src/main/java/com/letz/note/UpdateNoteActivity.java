package com.letz.note;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNoteActivity extends AppCompatActivity
{
    EditText memoUpdate;
    Button cancelUpdate;
    Button saveUpdate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update Note");
        setContentView(R.layout.activity_update_note);

        memoUpdate = findViewById(R.id.memoUpdate);
        cancelUpdate = findViewById(R.id.btn_cancel_activity_update_note);
        saveUpdate = findViewById(R.id.btn_save_activity_update_note);

        getData();

        cancelUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
//                Toast.makeText(UpdateNoteActivity.this, "nothing updated", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        saveUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                updateNote();
            }
        });
    }

    private void updateNote() {
        Intent i =  new Intent();
        String userNote = memoUpdate.getText().toString();
        i.putExtra("note",userNote);
        setResult(Activity.RESULT_OK,i);
        finish();
    }

    public void getData() {
        Intent updateI = getIntent();
        int updateId = updateI.getIntExtra("id", -1);
        String updateDescription =  updateI.getStringExtra("description");

        memoUpdate.setText(updateDescription);
    }


}