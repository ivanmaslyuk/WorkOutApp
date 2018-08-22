package com.example.admin.workoutapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;

import com.example.admin.workoutapp.model.Exercise;
import com.example.admin.workoutapp.model.ExerciseRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Exercise>> exercises;
    private ExerciseRepository repository;

    public MainViewModel(Application application) {
        super(application);
        this.repository = new ExerciseRepository(application);
    }

    public LiveData<List<Exercise>> getExercises() {
        if (exercises == null) {
            exercises = repository.getExercises();
        }
        return exercises;
    }
}
