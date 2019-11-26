package com.group0562.adventureofpost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.shapeClicker.ui.ShapeClickerActivity;
import com.group0562.adventureofpost.sudoku.ui.SudokuActivity;
import com.group0562.adventureofpost.trivia.Trivia;
import com.group0562.adventureofpost.trivia.ui.TriviaActivity;

public class GameActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.group0562.AdventureOfPost.MESSAGE";
    private Trivia trivia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        trivia = new Trivia();
    }

    public void onClickShapeClicker(View view) {
        Intent intent = new Intent(this, ShapeClickerActivity.class);
        TextView textView = findViewById(R.id.CCButton);
        String message = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onClickSudoku(View view) {
        Intent intent = new Intent(this, SudokuActivity.class);
        TextView textView = findViewById(R.id.SudokuButton);
        String message = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onClickTrivia(View view) {
        Intent intent = new Intent(this, TriviaActivity.class);
        TextView textView = findViewById(R.id.TriviaButton);
        String message = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra("game", trivia);
        startActivity(intent);
    }
}
