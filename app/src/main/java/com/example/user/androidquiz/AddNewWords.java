package com.example.user.androidquiz;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by User on 5.4.2017.
 */
public class AddNewWords extends ActionBarActivity {

    EditText eng;
    EditText turk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eng = (EditText) findViewById(R.id.englishWord);
        turk = (EditText) findViewById(R.id.turkishWord);

    }

    public void onClickFunction(View V) {
        String engW = eng.getText().toString();
        String turkW = turk.getText().toString();

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("words.txt", Context.MODE_APPEND));

                outputStreamWriter.write(engW+"-"+turkW);
                outputStreamWriter.write("\n");

            //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }



    }

    public void newWords() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {

            //onBackPressed();

            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
