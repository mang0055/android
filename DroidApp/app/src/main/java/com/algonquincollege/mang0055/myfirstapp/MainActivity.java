package com.algonquincollege.mang0055.myfirstapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

/**
 * { Guessing Game created for Android Development Assignment }
 *
 * @author {mang0055@algonquinlive.com}
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    EditText edit_input;
    Button btn_guess;
    int theNumber;
    public static String TAG = "MainActivity";
    private static final String ABOUT_DIALOG_TAG = "About Dialog";
    int count = 0;
    static String btn_text_guess = "Guess";
    static String btn_text_reset = "Reset";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_input = (EditText) findViewById(R.id.edit_input);
        btn_guess = (Button) findViewById(R.id.btn_guess);
        btn_guess.setText(btn_text_guess);
        btn_guess.setOnClickListener(this);
        btn_guess.setOnLongClickListener(this);
        theNumber = generateRandomNumber();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_guess:
                if (edit_input.getText().toString().isEmpty()) {
                    edit_input.setError("Guess some number first.");
                    return;
                }
                if (btn_guess.getText().toString().equals(btn_text_guess)) {
                    try {
                        int userGuess = Integer.parseInt(edit_input.getText().toString());
                        if (userGuess == theNumber) {
                            won(count);
                        } else if (userGuess < theNumber) {
                            guessIsLow(count);
                        } else if (userGuess > theNumber) {
                            guessIsHigh(count);
                        }
                    } catch (NumberFormatException e) {
                        edit_input.setError("Enter number only. (0 to 1000)");
                        return;
                    }
                } else if (btn_guess.getText().toString().equals(btn_text_reset)) {
                    count = 0;
                    edit_input.setText("");
                    theNumber = generateRandomNumber();
                    btn_guess.setText(btn_text_guess);
                }


                break;
        }
    }

    private void guessIsHigh(int count) {
        this.count++;
        if (count <= 10) {
            Toast.makeText(getApplicationContext(), "Your guess " + edit_input.getText().toString() + " is high !", Toast.LENGTH_SHORT).show();
        } else {
            resetGameMsg();
        }
    }

    private void guessIsLow(int count) {
        this.count++;
        if (count <= 10) {
            Toast.makeText(getApplicationContext(), "Your guess " + edit_input.getText().toString() + " is low !", Toast.LENGTH_SHORT).show();
        } else {
            resetGameMsg();
        }
    }

    private void won(int count) {
        String msg;
        if (count < 5) {
            msg = "Superior win!";
        } else if (count > 6 && count < 10) {
            msg = "Excellent win!";
        } else {
            resetGameMsg();
            return;
        }
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        btn_guess.setText(btn_text_reset);
    }

    private void resetGameMsg() {
        btn_guess.setText(btn_text_reset);
        Toast.makeText(getApplicationContext(), "You are Done with moves.\n\nPlease reset the Game to restart.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.btn_guess:
                Toast.makeText(getApplicationContext(), "You're winning guess number is " + theNumber, Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    private int generateRandomNumber() {
        int max = 1000;
        Random r = new Random();
        int i1 = r.nextInt(max + 1);
        Log.e(TAG, i1 + " Random Number");
        return i1;
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

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAttempts() {

    }
}
