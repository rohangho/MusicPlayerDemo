package com.getterexample.android.musicplayer;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private MediaPlayer mp = null;
    SurfaceView mSurfaceView=null;

    Button online;
    Button offline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        online=(Button)findViewById(R.id.online);
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        offline=(Button)findViewById(R.id.offline);
        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(getApplicationContext(),Offline.class);
                startActivity(myintent);
            }
        });
        mp = new MediaPlayer();
        mSurfaceView = (SurfaceView) findViewById(R.id.surface);
        mSurfaceView.setZOrderMediaOverlay(true);
        SurfaceHolder sh=mSurfaceView.getHolder();
        sh.setFormat(PixelFormat.TRANSLUCENT);
        mSurfaceView.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        mp=MediaPlayer.create(getApplicationContext(), getResources().getIdentifier("background","raw",getPackageName()));
        mp.start();

        int videoWidth = mp.getVideoWidth();
        int videoHeight = mp.getVideoHeight();


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
       int  width = size.x;
       int  height = size.y;

        android.view.ViewGroup.LayoutParams lp = mSurfaceView.getLayoutParams();

        lp.width = width;

        lp.height = height;

        //Commit the layout parameters
        mSurfaceView.setLayoutParams(lp);

        //Start video
        mp.setDisplay(holder);
        mp.start();
        mp.setLooping(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
