package com.example.mobilegamedevteamproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Enemy2 {
    Context context;
    Bitmap enemy2Ship;
    int enemy2X,enemy2Y;
    int enemy2Speed;
    Random number;

    public Enemy2(Context context) {
        this.context = context;
        enemy2Ship = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy2);


        number = new Random();

        enemy2X = 100 + number.nextInt(600);
        enemy2Y = 200;
        enemy2Speed = 9 + number.nextInt(12);
    }

    public Bitmap enemy2Bitmap(){ // Method for getting the bitmap
        return enemy2Ship;
    }

    public int enemy2Width(){
        return enemy2Ship.getWidth(); // Method for getting the enemy bitmap Width
    }

    public int enemy2Height(){
        return enemy2Ship.getHeight(); // Method for getting the enemy bitmap Height
    }
}
