package com.example.connectfour;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class GameOptions extends AppCompatActivity {

    private RadioGroup difficultyRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_options);

        difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);

        // Set up listener to capture selected difficulty and return it to MainActivity
        difficultyRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedButton = findViewById(checkedId);
            String selectedDifficulty = selectedButton.getText().toString();

            // Prepare result to return to MainActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selected_difficulty", selectedDifficulty);
            setResult(RESULT_OK, resultIntent);
            finish(); // Close GameOptions activity
        });
    }
}