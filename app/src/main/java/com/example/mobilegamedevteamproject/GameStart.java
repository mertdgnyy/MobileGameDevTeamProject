package com.example.mobilegamedevteamproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameStart extends AppCompatActivity {

    GameScreen screen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen = new GameScreen(this);
        setContentView(screen);


    }

//    public void GameStart(View view){
//
//        startActivity(new Intent(this,MainActivity.class));
//        finish();
//    }
}