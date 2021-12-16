package com.example.mobilegamedevteamproject;
//Metehan Kundak-Mert Doganay MobileGameDev Team Project
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EndGame extends AppCompatActivity {

    TextView Score;


    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgame);
        int score = getIntent().getExtras().getInt("score");
        Score = findViewById(R.id.score);
        Score.setText("" + score);
    }

    public void tryAgain(View view) {
        Intent intent = new Intent(EndGame.this, GameStart.class);
        startActivity(intent);
        finish();
    }

    public void end(View view) {
        finish();
    }
}
