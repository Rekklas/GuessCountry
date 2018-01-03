package com.kovedward.android.guesscountry.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kovedward.android.guesscountry.model.Country;

import java.util.List;

/**
 * Interface that contains the methods used for accessing the database
 */
@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries")
    List<Country> getAll();

    @Query("SELECT * FROM countries WHERE id IN (:countriesVariants)")
    List<Country> getCountriesForQuestion(List<Integer> countriesVariants);

    @Query("SELECT COUNT(*) FROM countries")
    int getNumberOfCountries();

    @Insert
    void insertAll(List<Country> countries);
}
