package com.example.mobilegamedevteamproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends View {

    Context context;
    Bitmap background;
    Handler handler;
    Runnable runnable;
    static int dx,dy;
    Rect rectangle;
    Player playerShip;
    long update = 30;
    Enemy enemyShip;
    ArrayList<Missile> PlayerMissiles;
    ArrayList<Missile> EnemyMissiles;
    boolean enemyShot = false;
    Random number;

    public GameScreen(Context context) {
        super(context);
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(),R.drawable.space);

        Display disp = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point dimension = new Point();
        disp.getSize(dimension);
        dx = dimension.x; //Screen width
        dy = dimension.y; //Screen Height
        rectangle = new Rect(0, 0, dx, dy);
        PlayerMissiles = new ArrayList<>();
        EnemyMissiles = new ArrayList<>();
        playerShip = new Player(context);
        enemyShip = new Enemy(context);
        number = new Random();

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();

            }
        };

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(background,null,rectangle,null);
        canvas.drawBitmap(playerShip.get_Player(), playerShip.playerX, playerShip.playerY, null);

        //Limiting the player ship from exceeding the borders of the screen by taking the bitmap's x coordination and width.
        if(playerShip.playerX > dx - playerShip.playerWidth()){
            playerShip.playerX = dx - playerShip.playerWidth();
        }else if(playerShip.playerX < 0){
            playerShip.playerX = 0;
        }

        // We are moving the enemy ship with increasing its speed
        enemyShip.enemyX += enemyShip.enemySpeed;

        // If enemyShip collapses with the right wall, reversing the speed, moving to ship left side
        if(enemyShip.enemyX + enemyShip.enemyWidth() >= dx){
            enemyShip.enemySpeed *= -1;

        }
        // If enemyShip collapses with the left wall, reversing the speed, moving to ship right side
        if(enemyShip.enemyX <=0){
            enemyShip.enemySpeed *= -1;

        }

        //If enemyShot is false (No shots left , array list < 1), enemyShip send Missiles randomly with the specified traveling ranges.
        if(enemyShot == false){
            if(enemyShip.enemyX >= 350 + number.nextInt(500)){
                Missile enemyMissle = new Missile(context, enemyShip.enemyX + enemyShip.enemyWidth() /2, enemyShip.enemyY);
                EnemyMissiles.add(enemyMissle);
                enemyShot = true;
            }
            if(enemyShip.enemyX >= 500 + number.nextInt(700)){
                Missile enemyMissle = new Missile(context, enemyShip.enemyX + enemyShip.enemyWidth() /2, enemyShip.enemyY);
                EnemyMissiles.add(enemyMissle);
                enemyShot = true;
            }if(enemyShip.enemyX >= 700 + number.nextInt(900)){
                Missile enemyMissle = new Missile(context, enemyShip.enemyX + enemyShip.enemyWidth() /2, enemyShip.enemyY);
                EnemyMissiles.add(enemyMissle);
                enemyShot = true;
            }
            else{
                Missile enemyMissle = new Missile(context, enemyShip.enemyX + enemyShip.enemyWidth() /2, enemyShip.enemyY);
                EnemyMissiles.add(enemyMissle);
                enemyShot = true;
            }

        }

        //Drawing the enemyship Missles.
        for (int i =0; i<EnemyMissiles.size(); i++){
            EnemyMissiles.get(i).Missile_y += 30;
            canvas.drawBitmap(EnemyMissiles.get(i).getMissile(), EnemyMissiles.get(i).Missile_x,EnemyMissiles.get(i).Missile_y, null);

            if(EnemyMissiles.get(i).Missile_y >= dy){
                EnemyMissiles.remove(i);
            }

            if(EnemyMissiles.size() < 1){
                enemyShot = false;
            }

        }


        for (int i =0; i<PlayerMissiles.size(); i++){
            PlayerMissiles.get(i).Missile_y -= 30;
            canvas.drawBitmap(PlayerMissiles.get(i).getMissile(), PlayerMissiles.get(i).Missile_x,PlayerMissiles.get(i).Missile_y, null);
        }



        canvas.drawBitmap(enemyShip.enemyBitmap(), enemyShip.enemyX, enemyShip.enemyY, null);

        handler.postDelayed(runnable,update);












    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){


            case MotionEvent.ACTION_UP:
                Missile new_missile = new Missile(context, playerShip.playerX, playerShip.playerY);
                PlayerMissiles.add(new_missile);


//            // If event is Down, control the player ship.
//            case MotionEvent.ACTION_DOWN:


                // If event is Move, control the player ship with touch.
            case MotionEvent.ACTION_MOVE:
                playerShip.playerX = (int)event.getX();

                break;

        }
        return true;
    }


}
