package com.example.admin.workoutapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.workoutapp.viewmodel.MainViewModel;
import com.example.admin.workoutapp.R;
import com.example.admin.workoutapp.model.Exercise;
import com.example.admin.workoutapp.model.ExerciseRepository;
import com.example.admin.workoutapp.utils.ExerciseAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel vm;
    private String m_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm = ViewModelProviders.of(this).get(MainViewModel.class);

        FloatingActionButton addExerciseButton = findViewById(R.id.main_add_axercise_button);
        addExerciseButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddExerciseActivity.class)));

        vm.getExercises().observe(this, this::updateExerciseList);
    }

    private void updateExerciseList(List<Exercise> exercises) {
        ListView lv = findViewById(R.id.main_exercise_list);
        ExerciseAdapter adapter = new ExerciseAdapter(this, exercises, getApplication());
        lv.setAdapter(adapter);
    }
}
