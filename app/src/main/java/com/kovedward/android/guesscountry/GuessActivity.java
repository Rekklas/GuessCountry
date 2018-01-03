package com.kovedward.android.guesscountry;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kovedward.android.guesscountry.database.AppDatabase;
import com.kovedward.android.guesscountry.model.Country;

import java.util.List;

public class GuessActivity extends AppCompatActivity {

    private AppDatabase database;

    private Button leftUpButton;
    private Button leftDownButton;
    private Button rightUpButton;
    private Button rightDownButton;
    private TextView foodHint;
    private TextView animalHint;
    private TextView otherHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        leftUpButton = findViewById(R.id.button_country_left_up);
        leftDownButton = findViewById(R.id.button_country_left_down);
        rightUpButton = findViewById(R.id.button_country_right_up);
        rightDownButton = findViewById(R.id.button_country_right_down);

        foodHint = findViewById(R.id.textview_food_hint);
        animalHint = findViewById(R.id.textview_animal_hint);
        otherHint = findViewById(R.id.textview_other_hint);

        new DatabaseTask().execute();

        foodHint.setVisibility(View.VISIBLE);
        animalHint.setVisibility(View.VISIBLE);
        otherHint.setVisibility(View.VISIBLE);
    }

    private class DatabaseTask extends AsyncTask<String, Void, List<Country>> {
        /**
         * The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute()
         */
        protected List<Country> doInBackground(String... urls) {
            database = AppDatabase.getAppDatabase(getApplicationContext());

            return database.countryDao().getAll();
        }

        /**
         * The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground()
         */
        protected void onPostExecute(List<Country> result) {

        }
    }
}
