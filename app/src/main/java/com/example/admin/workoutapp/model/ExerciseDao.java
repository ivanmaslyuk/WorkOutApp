package com.example.admin.workoutapp.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long addExercise(Exercise exercise);

    @Query("SELECT * FROM exercises")
    LiveData<List<Exercise>> getExercises();

    @Delete
    void delete(Exercise exercise);

    @Query("SELECT * FROM exercises WHERE name LIKE :name LIMIT 1")
    LiveData<Exercise> getExercise(String name);

    @Update
    void update(Exercise exercise);
}
