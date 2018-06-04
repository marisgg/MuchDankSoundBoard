package com.tolhuis.muchdanksoundboard;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
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
        String[] filenames = new String[]{
                "s9_boom", "s9_scum_gang", "s9_i_let_my_nuts_hang", "s9_im_not_dumb"};
        LinearLayout linearLayout = findViewById(R.id.buttonlayout);
        String id = "";
        for(int i = 0; i < filenames.length; i++) {
                if (!id.equals(filenames[i].substring(0,2))){
                    id = filenames[i].substring(0,2);
                    ImageView iv = new ImageView(this);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    iv.setAdjustViewBounds(true);
                    iv.setImageResource(getResources().getIdentifier(id, "drawable", getPackageName()));
                    if (!id.equals("s9")) {
                        Button pad = new Button(this);
                        pad.setVisibility(View.INVISIBLE);
                        linearLayout.addView(pad);
                    }
                    linearLayout.addView(iv);
                }
                Button b = new Button(this);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound(v);
                    }
                });
                b.setText(filenames[i].substring(3).replaceAll("_", " "));
                buttonMap.put(b, i);
                linearLayout.addView(b);
                mps.add(MediaPlayer.create(this, getResources().getIdentifier(filenames[i], "raw", getPackageName())));
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
