package com.example.admin.workoutapp.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

public class ExerciseRepository {

    private ExerciseDao dao;

    public ExerciseRepository(Application application) {
        this.dao = AppDatabase.getAppDatabase(application).exerciseDao();
    }

    public LiveData<List<Exercise>> getExercises() {
        return dao.getExercises();
    }

    public long addExercise(Exercise e) {
        return dao.addExercise(e);
    }

    public LiveData<Exercise> getExercise(String name) {
        return dao.getExercise(name);
    }

    public void updateExercise(Exercise exercise) {
        dao.update(exercise);
    }
}
