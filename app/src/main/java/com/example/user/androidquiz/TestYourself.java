package com.example.user.androidquiz;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

/**
 * Created by User on 5.4.2017.
 */
public class TestYourself extends ActionBarActivity {

    double score;
    int questionNumber;
    String correctAnswer;

    TextView question;
    TextView questionNumberT;
    TextView scorenew;
    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;

    Random random;

    List<String> w = new ArrayList<>();
    List<String> listT = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        random = new Random();
        scorenew = (TextView) findViewById(R.id.score);
        score = 0;
        questionNumber = 0;
        correctAnswer = "";

        questionNumberT = (TextView) findViewById(R.id.questionNumber);
        question = (TextView) findViewById(R.id.question);
        YoYo.with(Techniques.FlipInY)
                .duration(700)
                .repeat(1)
                .playOn(findViewById(R.id.question));

        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);

        readFile();

        startTest();
    }

    public void startTest() {

        if(w.size() > 1) {
            int i = generateRandomNumber(w.size());
            String[] parts = w.get(i).split("-");


            question.setText(parts[0]);
            question.invalidate();



            w.remove(i);

            correctAnswer = parts[1];

            String[] b = generateWrongAnswers();

            /*bt1.setText(b[0]);
            bt2.setText(b[1]);
            bt3.setText(b[2]);
            bt4.setText(correctAnswer);*/

        }else {
            question.setText("Your Score " + score);
            question.invalidate();
            bt1.setVisibility(View.INVISIBLE);
            bt2.setVisibility(View.INVISIBLE);
            bt3.setVisibility(View.INVISIBLE);
            bt4.setVisibility(View.INVISIBLE);

        }

    }

    public void answer1(View v) {
        String t = bt1.getText().toString();

        if(t.equals(correctAnswer)) {

            score+=1;
            scorenew.setText("Your Score " + score);
           // Toast.makeText(this,"Correct "+t+" Score :"+score,Toast.LENGTH_LONG).show();
            startTest();
        }else {

            score-=0.5;
            scorenew.setText("Your Score " + score);
            startTest();
        }
    }

    public void answer2(View v) {
        String t = bt2.getText().toString();

        if(t.equals(correctAnswer)) {

            score+=1;
            scorenew.setText("Your Score " + score);
            startTest();
        }else {

            score-=0.5;
            scorenew.setText("Your Score " + score);
            startTest();
        }
    }
    public void answer3(View v) {
        String t = bt3.getText().toString();

        if(t.equals(correctAnswer)) {

            score+=1;
            scorenew.setText("Your Score " + score);
            startTest();
        }else {

            score-=0.5;
            scorenew.setText("Your Score " + score);
            startTest();
        }
    }
    public void answer4(View v) {
        String t = bt4.getText().toString();

        if(t.equals(correctAnswer)) {

            score+=1;
            scorenew.setText("Your Score " + score);
            startTest();
        }else {

            score-=0.5;
            scorenew.setText("Your Score " + score);
            startTest();
        }
    }

    public static List<String> cloneList(List<String> list) {
        List<String> clone = new ArrayList<>(list.size());
        for(int i = 0; i < list.size(); i++) {
            String[] parts = list.get(i).split("-");
            clone.add(parts[1].toString());
        }
        return clone;
    }

    public String[] generateWrongAnswers() {
        readTwords();
        //List<String> cloneList = cloneList(listT);
        String[] wrongAnswers = new String[3];
        List<String> correct = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            int y = generateRandomNumber(listT.size());

            if(!listT.get(y).equals(correctAnswer) && !correct.contains(listT.get(y))) {
                wrongAnswers[i] = listT.get(y);
                //listT.remove(y);
                correct.add(listT.get(y));
            }else {
                i--;
            }
        }
        int x = generateRandomNumber(3);
        if(x == 0) {
            bt1.setText(correctAnswer);
            bt2.setText(wrongAnswers[0]);
            bt3.setText(wrongAnswers[1]);
            bt4.setText(wrongAnswers[2]);
        }else if(x == 1 ) {
            bt2.setText(correctAnswer);
            bt1.setText(wrongAnswers[0]);
            bt3.setText(wrongAnswers[1]);
            bt4.setText(wrongAnswers[2]);
        }else if(x == 2) {
            bt3.setText(correctAnswer);
            bt1.setText(wrongAnswers[0]);
            bt2.setText(wrongAnswers[1]);
            bt4.setText(wrongAnswers[2]);
        }else if(x == 3) {
            bt4.setText(correctAnswer);
            bt1.setText(wrongAnswers[0]);
            bt2.setText(wrongAnswers[1]);
            bt3.setText(wrongAnswers[2]);
        }

        return wrongAnswers;
    }

    public void readTwords() {
        try {
            InputStream inputStream = openFileInput("words.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";


                while ((receiveString = bufferedReader.readLine()) != null) {

                    String[] parts = receiveString.split("-");
                    listT.add(parts[1]);
                }



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


    public int generateRandomNumber(int range) {
        return random.nextInt(range);
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
               // Toast.makeText(this, w.get(i - 1).toString() + w.size(), Toast.LENGTH_LONG).show();
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
