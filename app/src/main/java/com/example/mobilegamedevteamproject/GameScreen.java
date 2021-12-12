package com.example.mobilegamedevteamproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;

public class GameScreen extends View {

    Context context;
    Bitmap background;
    Handler handler;
    Runnable runnable;
    public GameScreen(Context context) {
        super(context);
        this.context = context;
    }
}
