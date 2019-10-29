package com.group0562.adventureofpost;

import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.group0562.adventureofpost.circleClicker.views.CircleClickerActivity;
import com.group0562.adventureofpost.trivia.Trivia;
import com.group0562.adventureofpost.trivia.views.TriviaActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //TODO I dont know if this is correct
    public final static String EXTRA_MESSAGE = "com.group0562.AdventureOfPost.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //TODO Currently will bring user to the SudokuActivity screen
    public void sendMessage(View view) throws IOException {
        Intent intent = new Intent(this, TriviaActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
