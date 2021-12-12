package com.example.mobilegamedevteamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.mainmenu);
        setContentView(new GameScreen(this));
    }


    public void GameStart(View view){
        Intent intent = new Intent(this,GameStart.class);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}