package com.getterexample.android.musicplayer;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class Offline extends AppCompatActivity {

    ArrayList<String>  list_of_music=new ArrayList<String>();
    RecyclerView mrecycle;
    OfflineAdapter offlineAdapter;
    private MediaPlayer player=null;
    private SeekBar seekMe;
    RecyclerView.LayoutManager layoutManager;
    Uri songUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //  Toast.makeText(this,"hiiiiiiiii",Toast.LENGTH_LONG).show();
                getlist();
            } else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

            }
        } else {
            getlist();
        }

        seekMe=(SeekBar)findViewById(R.id.seek);
        player=new MediaPlayer();
        mrecycle=(RecyclerView)findViewById(R.id.musiclist);
        layoutManager = new LinearLayoutManager(this);
        mrecycle.setLayoutManager(layoutManager);
        offlineAdapter = new OfflineAdapter(list_of_music, this);
        mrecycle.setAdapter(offlineAdapter);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getlist();
            }
            else
                Toast.makeText(this,"permission not granted",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this,"permission not granted",Toast.LENGTH_LONG).show();
    }


    public void getlist()
    {
        ContentResolver content_resolver = getContentResolver();
        songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursorSong = content_resolver.query(songUri,null,null,null,null);
        if(cursorSong!=null && cursorSong.moveToFirst())
        {
            int songTitle=cursorSong.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songpath=cursorSong.getColumnIndex(MediaStore.Audio.Media.DATA);
            do {
                String currentTitle = cursorSong.getString(songTitle);
                String fullpath = cursorSong.getString(songpath);
        //        try {
       //             player.setDataSource(fullpath);
       //             player.prepare();
       //             player.start();
       //         } catch (IOException e) {
       //             e.printStackTrace();
       //         }
                list_of_music.add(currentTitle);

            }while (cursorSong.moveToNext());
        }
    }

    public void play()
    {

    }

}
