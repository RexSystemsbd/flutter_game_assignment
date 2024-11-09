package com.example.connectfour;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> optionsLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set padding for system insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize buttons
        Button connectFourButton = findViewById(R.id.connect_btn);
        Button optionsButton = findViewById(R.id.options_btn);

        // Initialize the ActivityResultLauncher
        optionsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String difficulty = result.getData().getStringExtra("selected_difficulty");

                        // Inflate custom toast layout
                        View customToastView = getLayoutInflater().inflate(R.layout.custom_toast, null);
                        TextView toastText = customToastView.findViewById(R.id.toastText);
                        toastText.setText(difficulty + " Mode");

                        // Create and show the custom Toast
                        Toast toast = new Toast(this);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(customToastView);
                        toast.show();
                }}
        );

        // Set onClickListener to open Board activity
        connectFourButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Board.class);
            startActivity(intent);
        });

        // Set onClickListener to open GameOptions Activity
        optionsButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameOptions.class);
            optionsLauncher.launch(intent);
        });
    }
}
