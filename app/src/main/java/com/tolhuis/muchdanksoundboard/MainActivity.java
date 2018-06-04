package com.tolhuis.muchdanksoundboard;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Map<Button, Integer> buttonMap;
    List<MediaPlayer> mps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonMap = new HashMap<>();
        mps = new ArrayList<>();
        LinearLayout linearLayout = findViewById(R.id.buttonlayout);
        for(int i = 0, j = 0; i < linearLayout.getChildCount(); i++) {
            if(linearLayout.getChildAt(i) instanceof Button) {
                buttonMap.put((Button) linearLayout.getChildAt(i), j);
                mps.add(MediaPlayer.create(this, getResources().getIdentifier("s" + j, "raw", getPackageName())));
                j++;
            }
        }

    }

    public void playSound(View v) {
        for (MediaPlayer mp : mps)
                if(mp.isPlaying()) {
                    mp.pause();
                    mp.seekTo(0);
                }
        mps.get(buttonMap.get((Button)v)).start();
    }

    @Override
    public void onStop() {
        super.onStop();
        for (MediaPlayer mp : mps)
            if(mp.isPlaying()) {
                mp.pause();
                mp.seekTo(0);
            }
    }








}
