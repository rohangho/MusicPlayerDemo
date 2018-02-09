package com.getterexample.android.musicplayer;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Offline extends AppCompatActivity {

    ArrayList<String>  list_of_music=new ArrayList<String>();
    RecyclerView mrecycle;
    OfflineAdapter offlineAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        if (Build.VERSION.SDK_INT >= 23)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE))
                getlist();
            else
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        else
            getlist();

        mrecycle=(RecyclerView)findViewById(R.id.musiclist);
        layoutManager = new LinearLayoutManager(this);
        mrecycle.setLayoutManager(layoutManager);
        offlineAdapter = new OfflineAdapter(list_of_music, this);
        mrecycle.setAdapter(offlineAdapter);

    }

    public void getlist()
    {
        ContentResolver content_resolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursorSong = content_resolver.query(songUri,null,null,null,null);
        if(cursorSong!=null && cursorSong.moveToFirst())
        {
            int songTitle=cursorSong.getColumnIndex(MediaStore.Audio.Media.TITLE);
            do {
                String currentTitle = cursorSong.getString(songTitle);
                list_of_music.add(currentTitle);
            }while (cursorSong.moveToNext());
        }
    }

}
