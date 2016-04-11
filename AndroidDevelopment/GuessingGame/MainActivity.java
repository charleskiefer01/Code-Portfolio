package com.example.guessinggame_cak;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.*;

import java.util.Random;

import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/*
	This project implements a very simple guessing game
	The concept has been done many times before, so it makes for an excellent test project
	A random number between set values is generated and the user must attempt to guess it within a formula-determined number of guesses
	The most efficient way to find it is, of course, a binary search
*/

public class MainActivity extends Activity implements OnClickListener {

    //Set up variables for all GUI components
    private EditText txtGuessField;
    public Button guessButton;
    public Button btnPlayAgain;
    public TextView lblGuessResult;

    Random rand = new Random();

    int min = 1;
    public int max = 100;
    public int loseDisabled = 0;

    int playerGuesses = 7;

    int randomNum;

    boolean loseState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Associate local variables with the view layout

        newGame();

        initGuessButton();
        initPlayAgainButton();
        initSettingsButton();

        int oldScore = getValueDefault("scoreKey", 0);
        loseDisabled = getValueDefault("loseStateKey", 0);

        Button btnPlayAgain = (Button) findViewById(R.id.buttonPlayAgain);
        btnPlayAgain.setVisibility(View.GONE);

        txtGuessField = (EditText) findViewById(R.id.txtGuessField);

        TextView lblHighScore = (TextView) findViewById(R.id.lblHighScore);
        lblHighScore.setText("High score: " + oldScore);

        txtGuessField.requestFocus();
        txtGuessField.selectAll();


        txtGuessField.setOnEditorActionListener( new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                guessFunction();
                return true;
            }
        });


    }

	// Generally, this is activated on returning to the main activity from the settings page
	// Since changing the settings needs to start a new game, this shares functionality with the onCreate() method
    protected void onResume(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGuessButton();
        initPlayAgainButton();
        initSettingsButton();

        int oldScore = getValueDefault("scoreKey", 0);
        loseDisabled = getValueDefault("loseStateKey", 0);

        Button btnPlayAgain = (Button) findViewById(R.id.buttonPlayAgain);
        btnPlayAgain.setVisibility(View.GONE);

        txtGuessField = (EditText) findViewById(R.id.txtGuessField);

        TextView lblHighScore = (TextView) findViewById(R.id.lblHighScore);
        lblHighScore.setText("High score: " + oldScore);

        txtGuessField.requestFocus();
        if(txtGuessField.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    // Function for accessing the SharedPreferences
    // It receives the key for what you want and a default value in case it doesn't exist
    public int getValueDefault(String key, int defaultInt)
    {
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);

        return prefs.getInt(key, defaultInt);
    }

    // Function for storing a value in the SharedPreferences
    // It receives an integer value, and the key for what you want to store it as
    public void storeValue(int value, String key)
    {
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);

        Editor edit = prefs.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Function for initializing a new game
    // Attempt at keeping onCreate's grubby fingers off the random number generator
    private void newGame()
    {
        loseDisabled = getValueDefault("loseStateKey", 0);
        max = (getValueDefault("rangeKey", 100));

        TextView lblGuessResult = (TextView) findViewById(R.id.lblGuessResult);
        TextView lblRange = (TextView) findViewById(R.id.lblRange);
        txtGuessField = (EditText) findViewById(R.id.txtGuessField);

        lblRange.setText("Guess a number between " + min + " and " + max);

        randomNum = rand.nextInt((max - min) + 1) + min;

        // Picks up the SharedPreference for range, then sets variables to match
        switch (max)
        {
            case 10:
                playerGuesses = 4;
                break;
            case 100:
                playerGuesses = 7;
                break;
            default:
                playerGuesses = 10;
        }

        // Resets text, in case it hasn't been done
        if (loseDisabled == 0)
        {
            lblGuessResult.setText("You have " + playerGuesses + " guesses left.");
        }
        else
        {
            lblGuessResult.setText("You have infinite guesses left. Coward.");
        }
    }

    // This is in a separate function from the button so it can also be called from the KeyListener

    // Logic path:
    // 1. Collect SharedPreferences in case losing is off
    // 2. Check if there's a value in the textField
    // 3. Collect the value in the textField and compare it to the randomNumber
    // 4. If the value is greater than or less than the randomNumber, subtract from guesses and change messages
    // 5. Check if guesses left is less than 1. If it is, display game over screen instead
    // 6. If the value == randomNum, show win screen
    // 7. If applicable, try to store the high score

    private void guessFunction()
    {
        loseDisabled = getValueDefault("loseStateKey", 0);

        Button guessButton = (Button) findViewById(R.id.guessButton);
        txtGuessField = (EditText) findViewById(R.id.txtGuessField);
        TextView lblGuessResult = (TextView) findViewById(R.id.lblGuessResult);
        Button btnPlayAgain = (Button) findViewById(R.id.buttonPlayAgain);

        try
        {
            int guessNum = Integer.parseInt(txtGuessField.getText().toString());
            if (guessNum > randomNum)
            {

                if (loseDisabled == 0)
                {
                    playerGuesses--;
                    lblGuessResult.setText("Your guess of " + guessNum + " was too high. " + playerGuesses + " guesses left.");
                }
                else
                {
                    lblGuessResult.setText("Your guess of " + guessNum + " was too high.");
                }

                if (playerGuesses < 1)
                {
                    txtGuessField.setVisibility(View.GONE);
                    guessButton.setVisibility(View.GONE);
                    lblGuessResult.setText("You're out of guesses. You get nothing! You lose!");
                    btnPlayAgain.setVisibility(View.VISIBLE);
                    loseState = true;
                }

            }
            else if (guessNum < randomNum)
            {

                if (loseDisabled == 0)
                {
                    playerGuesses--;
                    lblGuessResult.setText("Your guess of " + guessNum + " was too low. " + playerGuesses + " guesses left.");
                }
                else
                {
                    lblGuessResult.setText("Your guess of " + guessNum + " was too low.");
                }

                if (playerGuesses < 1)
                {
                    txtGuessField.setVisibility(View.GONE);
                    guessButton.setVisibility(View.GONE);
                    lblGuessResult.setText("You're out of guesses. You get nothing! You lose!");
                    btnPlayAgain.setVisibility(View.VISIBLE);
                    loseState = true;
                }

            }
            else // Guess is equal to the randomNumber
            {
                if (loseDisabled == 0)
                {
                    playerGuesses--;
                    lblGuessResult.setText("You got it with " + playerGuesses + " guesses left!");
                }
                else
                {
                    lblGuessResult.setText("You got it. I hope it was worth it.");
                }

                txtGuessField.setText("");
                txtGuessField.setVisibility(View.GONE);
                guessButton.setVisibility(View.GONE);
                btnPlayAgain.setVisibility(View.VISIBLE);
                loseState = true;

                int oldScore = getValueDefault("scoreKey", 0);

                if(playerGuesses > oldScore  && loseDisabled == 0)
                {
                    storeValue(playerGuesses, "scoreKey");

                    TextView lblHighScore = (TextView) findViewById(R.id.lblHighScore);
                    lblHighScore.setText("High score: " + playerGuesses);
                }

            }

        }
        catch(Exception ex)
        {

            // For testing if there's nothing in the textField
            if (isEmpty(txtGuessField))                                                                                 {
                Toast toast = Toast.makeText(getApplicationContext(), "Please enter an integer.", Toast.LENGTH_SHORT)   ;
                toast.show()                                                                                            ;
            }
        }

        finally
        {
            // Hopefully puts focus on textField so keyboard pulls up
            txtGuessField.requestFocus();
            txtGuessField.setText("");
            // I prefer using setText to clear the field, because clicking on it
            // will un-highlight the contents. Somewhat inconvenient


        }

    }

    // Special function for testing if a textField is empty
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private void initGuessButton() {
        final Button guessButton = (Button) findViewById(R.id.guessButton);
        guessButton.setOnClickListener(new OnClickListener () {

            public void onClick(View arg0)
            {
                guessFunction();
            }

        });
    }

    private void initPlayAgainButton() {

        final Button btnPlayAgain = (Button) findViewById(R.id.buttonPlayAgain);

        btnPlayAgain.setOnClickListener (
                new OnClickListener () {

                    public void onClick(View arg0) {

                        // This wasn't quite complex enough to warrant another method
                        Button guessButton = (Button) findViewById(R.id.guessButton);
                        txtGuessField = (EditText) findViewById(R.id.txtGuessField);

                        txtGuessField.setVisibility(View.VISIBLE);
                        guessButton.setVisibility(View.VISIBLE);
                        btnPlayAgain.setVisibility(View.GONE);
                        loseState = false;

                        newGame();

                    }

                });
    }



    private void initSettingsButton() {
        final Button btnSettings = (Button) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        // Auto-generated method stub

    }



}
