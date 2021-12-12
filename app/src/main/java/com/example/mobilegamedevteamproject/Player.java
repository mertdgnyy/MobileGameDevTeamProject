package com.example.mobilegamedevteamproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Player {

    Context context;
    Bitmap player_bitmap;
    Random number;
    int playerX,playerY;

    public Player(Context context){
        this.context = context;
        player_bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.player);
        number = new Random();
        playerX = number.nextInt(GameScreen.dx);
        playerY = GameScreen.dy - player_bitmap.getHeight();
    }

    //To reach from the GameScreen
    public Bitmap get_Player(){
        return player_bitmap;
    }

    int playerWidth(){
        return player_bitmap.getWidth();
    }
}
