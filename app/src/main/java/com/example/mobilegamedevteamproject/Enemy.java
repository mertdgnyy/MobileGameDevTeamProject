package com.example.mobilegamedevteamproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Enemy {
    Context context;
    Bitmap enemyShip;
    int enemyX,enemyY;
    int enemySpeed;
    Random number;

    public Enemy(Context context) {
        this.context = context;
        enemyShip = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);


        number = new Random();

        enemyX = 150+ number.nextInt(500);
        enemyY = 0;
        enemySpeed = 10 + number.nextInt(5);
    }

    public Bitmap enemyBitmap(){ // Method for getting the bitmap
        return enemyShip;
    }

    public int enemyWidth(){
        return enemyShip.getWidth(); // Method for getting the enemy bitmap Width
    }

    public int enemyHeight(){
        return enemyShip.getHeight(); // Method for getting the enemy bitmap Height
    }
}
