package com.example.admin.workoutapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.admin.workoutapp.model.Exercise;
import com.example.admin.workoutapp.model.ExerciseRepository;
import com.example.admin.workoutapp.view.ExerciseActivity;

public class ExerciseViewModel extends AndroidViewModel {

    private LiveData<Exercise> exercise;
    private ExerciseRepository repository;
    private String exerciseName;

    public ExerciseViewModel(Application app, String exerciseName) {
        super(app);
        this.exerciseName = exerciseName;
        this.repository = new ExerciseRepository(app);
    }

    public LiveData<Exercise> getExercise() {
        if (exercise == null) {
            exercise = repository.getExercise(exerciseName);
        }
        return exercise;
    }

    public String getExerciseName() { return exerciseName; }
}
