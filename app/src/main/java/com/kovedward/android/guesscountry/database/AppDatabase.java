package com.kovedward.android.guesscountry.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.kovedward.android.guesscountry.model.Country;

/**
 * Created by user on 03.01.2018.
 */

@Database(entities = {Country.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountryDao countryDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "countries.db")
                            .build();
            INSTANCE.countryDao().insertAll(
                    new Country("Italy", "pasta", "wolf", "football"),
                    new Country("Germany", "sausages", "bear", "hockey"),
                    new Country("France", "chocolate", "frog", "tennis"),
                    new Country("England", "oatmeal", "squirrel", "golf")
            );
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
