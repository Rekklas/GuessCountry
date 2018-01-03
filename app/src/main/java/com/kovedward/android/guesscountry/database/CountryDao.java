package com.kovedward.android.guesscountry.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kovedward.android.guesscountry.model.Country;

import java.util.List;

/**
 * Created by user on 03.01.2018.
 */
@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries")
    List<Country> getAll();

    @Insert
    void insertAll(Country... countries);
}
