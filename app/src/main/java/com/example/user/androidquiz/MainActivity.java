package com.example.user.androidquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {


    List<String> words = new ArrayList<>();
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("silentMode", false);

        //Toast.makeText(this,silent+"",Toast.LENGTH_LONG).show();
        if(!silent)
            generateFile();

        /*try {
            InputStream inputStream = openFileInput("words.txt");

            if (inputStream == null) {
                Toast.makeText(this, "You should take a back-up file!", Toast.LENGTH_LONG);
                generateFile();
            }else{
                Log.w("a","TTTTTTTTTTTTTTTTTTTT");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }*/
        //generateFile();
    }

    public void trainFunction(View v) {
        YoYo.with(Techniques.Tada)
                .duration(700)
                .repeat(5)
                .playOn(findViewById(R.id.button));
        Intent i = new Intent(this , TrainActivity.class);
     startActivity(i);
    }

    public void addOnClickFunction(View v) {
        Intent i = new Intent(this , AddNewWords.class);
        startActivity(i);
    }

    public void testOnClickFunction(View v) {
        Intent i = new Intent(this , TestYourself.class);
        startActivity(i);
    }


    public void generateList() {
        words.add(0,"car-araba");
        words.add(1,"pink-pembe");
        words.add(2,"horse-at");
        words.add(3,"computer-bilgisayar");
        words.add(4,"black-siyah");
        words.add(5,"file-dosya");
        words.add(6,"ability-yetenek");
        words.add(7,"people–insanlar");
        words.add(8,"who–kim");
        words.add(9,"day–gün");
        words.add(10,"sound–ses");
        words.add(11,"place–yer");
        words.add(12,"year–yıl");
        words.add(13,"live–canlı");
        words.add(14,"good–iyi");
        words.add(15,"sentence–cümle");
        words.add(16,"man–adam");
        words.add(17,"think–düşünmek");
        words.add(18,"great–büyük");
        words.add(19,"right–sağ");
        words.add(20,"old–eski");

    }

    public void generateFile() {
        generateList();

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("words.txt", Context.MODE_PRIVATE));
            for(int i = 0; i < words.size(); i++) {
                outputStreamWriter.write(words.get(i));
                outputStreamWriter.write("\n");
            }
            //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("silentMode", true);

        // Commit the edits!
        editor.commit();
    }


    public void exit(View V){

        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
