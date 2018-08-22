package com.example.admin.workoutapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.admin.workoutapp.utils.RecordsListConverter;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "exercises")
public class Exercise {

    @PrimaryKey
    @NonNull
    private String name;
    @TypeConverters({RecordsListConverter.class})
    private List<Integer> records;

    public Exercise(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getRecords() {
        if (records == null) {
            records = new ArrayList<>();
        }
        return records;
    }

    public void setRecords(List<Integer> records) {
        this.records = records;
    }
}
