package com.example.mobilegamedevteamproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//Player missile
public class Missile2 {

    Bitmap missile2;
    Context context;
    int Missile2_x;
    int Missile2_y;
    public Missile2 (Context context, int Missile2_x, int Missile2_y){
        this.context = context;
        missile2 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.laserr2);

        this.Missile2_x = Missile2_x;
        this.Missile2_y = Missile2_y;

    }

    public Bitmap getMissile2() {
        return missile2;
    }
}
