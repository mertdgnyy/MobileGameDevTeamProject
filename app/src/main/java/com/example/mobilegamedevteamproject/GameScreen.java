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

        playerShip = new Player(context);
        enemyShip = new Enemy(context);

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

        canvas.drawBitmap(enemyShip.enemyBitmap(), enemyShip.enemyX, enemyShip.enemyY, null);

        handler.postDelayed(runnable,update);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){

            // If event is Down, control the player ship.
            case MotionEvent.ACTION_DOWN:
                playerShip.playerX = (int)event.getX();
                break;

            // If event is Move, control the player ship with touch.
            case MotionEvent.ACTION_MOVE:
                playerShip.playerX = (int)event.getX();
                break;

        }
        return true;
    }
}
