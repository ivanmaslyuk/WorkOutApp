package com.example.admin.workoutapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admin.workoutapp.R;
import com.example.admin.workoutapp.model.Exercise;
import com.example.admin.workoutapp.viewmodel.ExerciseViewModel;
import com.example.admin.workoutapp.viewmodel.ExerciseViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseActivity extends AppCompatActivity {

    ExerciseViewModel vm;

    @BindView(R.id.exercise_view_sets)
    TextView setsText;
    @BindView(R.id.exercise_view_reps)
    TextView repsText;
    @BindView(R.id.exercise_view_rest)
    TextView restText;
    @BindView(R.id.exercise_view_name)
    TextView nameText;
    @BindView(R.id.exercise_view_type)
    TextView typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        ButterKnife.bind(this);

        String exerciseName = getIntent().getExtras().getString("exercise_key");
        vm = ViewModelProviders
                .of(this, new ExerciseViewModelFactory(this.getApplication(), exerciseName))
                .get(ExerciseViewModel.class);

        setTitle(vm.getExerciseName());

        vm.getExercise().observe(this, this::updateExerciseView);
    }

    private void updateExerciseView(Exercise exercise) {
        /*int mins = exercise.getRestSeconds() / 60;
        int secs = exercise.getRestSeconds() % 60;
        String rest = Integer.toString(mins) + ":" + Integer.toString(secs);
        String type = exercise.isCardio() ? getString(R.string.cardio) : getString(R.string.strength);

        setsText.setText(Integer.toString(exercise.getSets()));
        repsText.setText(Integer.toString(exercise.getReps()));
        restText.setText(rest);*/
        nameText.setText(exercise.getName());
        /*typeText.setText(type);*/
    }
}
