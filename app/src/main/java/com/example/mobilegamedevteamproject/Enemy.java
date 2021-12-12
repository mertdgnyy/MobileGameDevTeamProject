package com.example.mobilegamedevteamproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Enemy {
    Context context;
    Bitmap enemyShip;

    public Enemy(Context context) {
        this.context = context;
        enemyShip = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
    }
}
