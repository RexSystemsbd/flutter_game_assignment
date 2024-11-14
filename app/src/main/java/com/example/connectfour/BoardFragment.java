package com.example.connectfour;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BoardFragment extends Fragment {
    private static final String GAME_STATE = "gameState";
    private ConnectFourGame mGame;
    private GridLayout mGrid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        mGame = new ConnectFourGame();
        mGrid = view.findViewById(R.id.gridLayout);
        mGrid.setRowCount(7);

        // Set up the 7x6 grid of circular buttons
        setupGrid();

        return view;
    }


    private void setupGrid() {
        int rows = 7;
        int columns = 6;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {

//change something in the params

                FloatingActionButton button = new FloatingActionButton(requireContext());
                GridLayout.LayoutParams params = (GridLayout.LayoutParams) button.getLayoutParams();
//                params.setMargins(2, 2, 2, 2);
//                button.setBackgroundResource(R.drawable.cell_background);
                button.setOnClickListener(this::onButtonClick);
//                button.setMinimumHeight(2);
//                button.setMinimumWidth(2);
//                button.setLayoutParams(params);
                mGrid.addView(button);
            }
        }
    }


    // Stub for button click handler
    private void onButtonClick(View view) {
        // Logic to handle button clicks can be implemented here
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, mGame.getState());
    }
}
