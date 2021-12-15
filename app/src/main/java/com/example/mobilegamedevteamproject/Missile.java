package com.example.mobilegamedevteamproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//Enemy missile
public class Missile {

    Bitmap missile;
    Context context;
    int Missile_x;
    int Missile_y;
    public Missile (Context context, int Missile_x, int Missile_y){
        this.context = context;
        missile = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.laserr);

        this.Missile_x = Missile_x;
        this.Missile_y = Missile_y;

    }

    public Bitmap getMissile() {
        return missile;
    }

    public int tWidth(){
        return missile.getWidth(); // It was in the GameScreen.java class that simply used for getting width of the bitmap
    }
    public int tHeight(){
        return missile.getHeight(); // It was in the GameScreen.java class that simply used for getting height of the bitmap
    }

}


