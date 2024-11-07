package com.example.connectfour;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        GridLayout gridLayout = findViewById(R.id.gameBoardGrid);

        // Loop to create and add 42 buttons (7 rows x 6 columns)
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 6; col++) {
                Button button = new Button(this);
                button.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                button.setBackgroundResource(R.drawable.circle_background); // Set white circular background
                button.setText("");
                button.setScaleY(R.style.CircleButton); // Apply CircleButton style

                // Add the button to the GridLayout
                gridLayout.addView(button);
            }
        }
    }
}
