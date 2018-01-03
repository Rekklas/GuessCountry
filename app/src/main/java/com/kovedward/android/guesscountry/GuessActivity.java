package com.kovedward.android.guesscountry;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kovedward.android.guesscountry.database.AppDatabase;
import com.kovedward.android.guesscountry.database.ListOfCountries;
import com.kovedward.android.guesscountry.model.Country;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
         *
         * This method gets database, if database is empty insert all the records of countries,
         * then it counts the number of records available in database
         *
         * @return List of 4 countries that have been randomly chosen by
         * getRandomIdsOfCountries method
         */
        protected List<Country> doInBackground(String... urls) {
            database = AppDatabase.getAppDatabase(getApplicationContext());

            if (database.countryDao().getNumberOfCountries() == 0){
                database.countryDao().insertAll(ListOfCountries.COUNTRIES);
            }

            int numberOfCountries = database.countryDao().getNumberOfCountries();
            Log.d("numberOfCountries:", String.valueOf(numberOfCountries));

            return database.countryDao().getCountriesForQuestion(getRandomIdsOfCountries(numberOfCountries));
        }

        /**
         * The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground()
         */
        protected void onPostExecute(List<Country> result) {
            leftUpButton.setText(result.get(0).getCountry());
            leftDownButton.setText(result.get(1).getCountry());
            rightUpButton.setText(result.get(2).getCountry());
            rightDownButton.setText(result.get(3).getCountry());

            foodHint.setText(result.get(0).getFood());
            animalHint.setText(result.get(0).getAnimal());
            otherHint.setText(result.get(0).getOther());
        }
    }

    /**
     * This method generate a Set of 4 numbers which represent countries ids,
     * then it transform this Set to ArrayList
     *
     * @param numberOfCountries Number of all available records of countries in database
     * @return List of ids of countries that have been chosen
     */
    public List<Integer> getRandomIdsOfCountries(int numberOfCountries){
        Random random = new Random(System.currentTimeMillis());
        Set<Integer> numberSet = new HashSet<>();

        while (numberSet.size() != 4) {
            numberSet.add((random.nextInt(numberOfCountries) + 1));
            Log.d("country:", String.valueOf(numberSet));
        }

        return new ArrayList<>(numberSet);
    }
}
