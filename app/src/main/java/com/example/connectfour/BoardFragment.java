package com.example.connectfour;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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

        if (savedInstanceState == null) {
            startGame();
        } else {
            String gameState = savedInstanceState.getString(GAME_STATE);
            mGame.setState(gameState);
            setDisc();
        }

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
                button.setOnClickListener(this::onButtonClick);
                mGrid.addView(button,params);
            }
        }
    }


    // Stub for button click handler
    private void onButtonClick(View view) {
        // Logic to handle button clicks can be implemented here
        int buttonIndex = mGrid.indexOfChild(view);
        int row = buttonIndex / ConnectFourGame.COL;
        int col = buttonIndex % ConnectFourGame.COL;

        mGame.selectDisc(row, col);
        setDisc();

        if (mGame.isGameOver()) {
            Toast.makeText(getContext(), "Congratulations! You won!", Toast.LENGTH_SHORT).show();
            mGame.newGame();
            setDisc();
        }
    }

    private void startGame() {
        mGame.newGame();
        setDisc();
    }

    private void setDisc() {
        for (int i = 0; i < mGrid.getChildCount(); i++) {
            FloatingActionButton gridButton = (FloatingActionButton) mGrid.getChildAt(i);
            int row = i / ConnectFourGame.COL;
            int col = i % ConnectFourGame.COL;

            Drawable emptyDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.circle_white);
            Drawable blueDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.circle_blue);
            Drawable redDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.circle_red);

            int discValue = mGame.getDisc(row, col);

            if (discValue == ConnectFourGame.BLUE) {
                gridButton.setBackground(blueDrawable);
            } else if (discValue == ConnectFourGame.RED) {
                gridButton.setBackground(redDrawable);
            } else {
                gridButton.setBackground(emptyDrawable);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, mGame.getState());
    }
}
