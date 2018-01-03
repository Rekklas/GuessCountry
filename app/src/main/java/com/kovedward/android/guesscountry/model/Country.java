package com.kovedward.android.guesscountry.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by user on 02.01.2018.
 */
@Entity(tableName = "countries", indices = {@Index(value = {"country"},
        unique = true)})
public class Country {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String country;

    private String food;
    private String animal;
    private String other;

    @Ignore
    public Country() {

    }

    public Country(String country, String food, String animal, String other) {
        this.country = country;
        this.food = food;
        this.animal = animal;
        this.other = other;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", food='" + food + '\'' +
                ", animal='" + animal + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
