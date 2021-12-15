package com.example.mobilegamedevteamproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    Enemy2 enemy2Ship;
    ArrayList<Missile2> PlayerMissiles;
    ArrayList<Missile> EnemyMissiles;
    ArrayList<Missile> Enemy2Missiles;
    boolean enemyShot = false;
    boolean enemy2Shot = false;
    Random number;
    int health = 6;
    int score = 0;
    int textSize = 70;
    boolean gameIs = true;
    Paint paintScore;


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
        Enemy2Missiles = new ArrayList<>();
        playerShip = new Player(context);
        enemyShip = new Enemy(context);
        enemy2Ship = new Enemy2(context);
        number = new Random();

        paintScore = new Paint();
        paintScore.setTextSize(textSize);
        paintScore.setTextAlign(Paint.Align.LEFT);
        paintScore.setColor(Color.WHITE);


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
        if(health == 0){
            gameIs = false;
        }
        canvas.drawBitmap(background,null,rectangle,null);
        canvas.drawText("Score: " + score, 0,textSize, paintScore);
        canvas.drawBitmap(playerShip.get_Player(), playerShip.playerX, playerShip.playerY, null);

        //Limiting the player ship from exceeding the borders of the screen by taking the bitmap's x coordination and width.
        if(playerShip.playerX > dx - playerShip.playerWidth()){
            playerShip.playerX = dx - playerShip.playerWidth();
        }else if(playerShip.playerX < 0){
            playerShip.playerX = 0;
        }

        // We are moving the enemy ship with increasing its speed
        enemyShip.enemyX += enemyShip.enemySpeed;
        enemy2Ship.enemy2X += enemy2Ship.enemy2Speed;

        // If enemyShip collapses with the right wall, reversing the speed, moving to ship left side
        if(enemyShip.enemyX + enemyShip.enemyWidth() >= dx){
            enemyShip.enemySpeed *= -1;

        }

        if(enemy2Ship.enemy2X + enemy2Ship.enemy2Width() >= dx){
            enemy2Ship.enemy2Speed *= -1;

        }
        // If enemyShip collapses with the left wall, reversing the speed, moving to ship right side
        if(enemyShip.enemyX <=0){
            enemyShip.enemySpeed *= -1;

        }
        if(enemy2Ship.enemy2X <=0){
            enemy2Ship.enemy2Speed *= -1;

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
        //If enemy2Shot is false (No shots left , array list < 1), enemy2Ship send Missiles randomly with the specified traveling ranges.
        if(enemy2Shot == false){
            if(enemy2Ship.enemy2X >= 250 + number.nextInt(400)){
                Missile enemy2Missile = new Missile(context, enemy2Ship.enemy2X + enemy2Ship.enemy2Width() /2, enemy2Ship.enemy2Y);
                Enemy2Missiles.add(enemy2Missile);
                enemy2Shot = true;
            }
            if(enemy2Ship.enemy2X >= 400 + number.nextInt(600)){
                Missile enemy2Missile = new Missile(context, enemy2Ship.enemy2X + enemy2Ship.enemy2Width() /2, enemy2Ship.enemy2Y);
                Enemy2Missiles.add(enemy2Missile);
                enemy2Shot = true;
            }if(enemy2Ship.enemy2X >= 600 + number.nextInt(800)){
                Missile enemy2Missile = new Missile(context, enemy2Ship.enemy2X + enemy2Ship.enemy2Width() /2, enemy2Ship.enemy2Y);
                Enemy2Missiles.add(enemy2Missile);
                enemy2Shot = true;
            }
            else{
                Missile enemy2Missile = new Missile(context, enemy2Ship.enemy2X + enemy2Ship.enemy2Width() /2, enemy2Ship.enemy2Y);
                Enemy2Missiles.add(enemy2Missile);
                enemy2Shot = true;
            }

        }

        //Drawing the enemyship Missles.
       for (int i =0; i<EnemyMissiles.size(); i++){
            EnemyMissiles.get(i).Missile_y += 30;
            canvas.drawBitmap(EnemyMissiles.get(i).getMissile(), EnemyMissiles.get(i).Missile_x,EnemyMissiles.get(i).Missile_y, null);

            if(EnemyMissiles.get(i).Missile_y >= dy){
               EnemyMissiles.remove(i);
            }
            if(EnemyMissiles.size() == 0){
                enemyShot = false;
            }
        }


        //Drawing the enemy2ship Missles.
        for (int i =0; i<Enemy2Missiles.size(); i++){
            Enemy2Missiles.get(i).Missile_y += 30;
            canvas.drawBitmap(Enemy2Missiles.get(i).getMissile(), Enemy2Missiles.get(i).Missile_x,Enemy2Missiles.get(i).Missile_y, null);
            if(Enemy2Missiles.get(i).Missile_y >= dy){
                Enemy2Missiles.remove(i);
            }

            if(Enemy2Missiles.size() == 0){
                enemy2Shot = false;
            }

        }


        for (int i =0; i<PlayerMissiles.size(); i++){
            PlayerMissiles.get(i).Missile2_y -= 50;
            canvas.drawBitmap(PlayerMissiles.get(i).getMissile2(), PlayerMissiles.get(i).Missile2_x,PlayerMissiles.get(i).Missile2_y, null);
                if(PlayerMissiles.get(i).Missile2_x <= enemy2Ship.enemy2X + enemy2Ship.enemy2Width()
                    && PlayerMissiles.get(i).Missile2_y <= enemy2Ship.enemy2Width()
                    && PlayerMissiles.get(i).Missile2_y >= enemy2Ship.enemy2Y && (PlayerMissiles.get(i).Missile2_x >= enemy2Ship.enemy2X)
                    || PlayerMissiles.get(i).Missile2_x <= enemyShip.enemyX + enemyShip.enemyWidth()
                    && PlayerMissiles.get(i).Missile2_y <= enemyShip.enemyWidth()
                    && PlayerMissiles.get(i).Missile2_y >= enemyShip.enemyY &&
                    PlayerMissiles.get(i).Missile2_x >= enemyShip.enemyX){
                    score++;
                    PlayerMissiles.remove(i);
                }

           else if(PlayerMissiles.get(i).Missile2_y <=0){
               PlayerMissiles.remove(i);}


        }

        canvas.drawBitmap(enemyShip.enemyBitmap(), enemyShip.enemyX, enemyShip.enemyY, null);
        canvas.drawBitmap(enemy2Ship.enemy2Bitmap(), enemy2Ship.enemy2X, enemy2Ship.enemy2Y, null);

        if(gameIs == true){
            handler.postDelayed(runnable,update);
        }



    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){


            case MotionEvent.ACTION_UP:
                Missile2 new_missile = new Missile2(context, playerShip.playerX + playerShip.playerWidth()/2, playerShip.playerY);
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
