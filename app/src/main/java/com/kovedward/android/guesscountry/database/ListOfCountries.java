package com.kovedward.android.guesscountry.database;

import com.kovedward.android.guesscountry.model.Country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class that contains the list of all records which will be
 * inserted into database during firrst installed
 */

public final class ListOfCountries {

    public static final List<Country> COUNTRIES = Collections.unmodifiableList(
            Arrays.asList(
                    new Country("Italy", "pasta", "wolf", "soccer"),
                    new Country("Germany", "sausages", "bear", "hockey"),
                    new Country("France", "chocolate", "frog", "tennis"),
                    new Country("England", "oatmeal", "squirrel", "golf"),
                    new Country("Ukraine", "vareniki", "boar", "golf"),
                    new Country("USA", "pizza", "eagle", "football")
            ));
}