package com.example.user.androidquiz;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by User on 5.4.2017.
 */
public class TrainActivity extends ActionBarActivity {

    List<String> w = new ArrayList<>();
    List<String> w2 = new ArrayList<>();

    Random random;

    TextView engText;

    int flag;
    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        random = new Random();
        engText = (TextView) findViewById(R.id.textEng);
        flag = 0;
        temp = "";
        readFile();
    }


    public void readFile() {
        //List<String> w = new ArrayList<>();
        try {
            InputStream inputStream = openFileInput("words.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {

                    w.add(receiveString);
                }

                int i = w.size();
              //  Toast.makeText(this, w.get(i - 1).toString() + w.size(), Toast.LENGTH_LONG).show();
              //  Toast.makeText(this, w.get(i - 1).toString() + w.size(), Toast.LENGTH_LONG).show();
                //Toast.makeText(this, w.size(), Toast.LENGTH_LONG).show();

                inputStream.close();


            } else {
                Toast.makeText(this, "There are no recovery files", Toast.LENGTH_LONG);
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
            Toast.makeText(this, "There are no recovery files", Toast.LENGTH_LONG);
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    public void onClickNext(View v) {


        YoYo.with(Techniques.FlipInY)
                .duration(700)
                .repeat(1)
                .playOn(findViewById(R.id.textEng));



        if (w.size() > 0) {
            int i = generateRandomNumber(w.size());
            String[] parts = w.get(i).split("-");
            if (flag % 2 == 0) {

                temp = parts[1];
                engText.setText(parts[0]);
                engText.invalidate();
                w2.add(w.remove(i));
            } else {
                engText.setText(temp);
                engText.invalidate();
            }

            flag++;
        } else {
            int size = w2.size();
            for (int i = 0; i < size; i++) {
                w.add(w2.remove(0));
            }
            engText.setText(temp);
            engText.invalidate();
        }


    }


    public int generateRandomNumber(int range) {
        return random.nextInt(range);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            //onBackPressed();

            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
