package com.tolhuis.muchdanksoundboard;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
                "ez_im_from_the_bay", "ez_in_the_flesh", "ez_kinda_get_chills", "ez_now_you_have_a_girlfriend", "ez_sniff", "ez_switzerland",
                "ez_white_rappers", "ez_you_are_a_hater", "jo_breaking_street_protocol", "jo_fuckety_fuck", "jo_hey_akademiks", "jo_if_a_nigga_post_your_girl",
                "jo_noooo", "jo_stop", "jo_stop_reporting", "jo_this_is_my_point", "jo_this_is_snitchin_number_one", "lo_also_not_fully_educated", "lo_and_also_biracial_people",
                "lo_black_people", "lo_even_more_gangsta", "lo_fuck_you", "lo_im_biracial", "lo_im_not_white", "lo_i_support", "lo_me_black_and_white", "lo_wtf_is_that", "s9_boom",
                "s9_im_not_dumb", "s9_i_let_my_nuts_hang", "s9_scum_gang", "ye_dont_call_me_fat", "ye_everyone_listen", "ye_im_in_the_hospital", "ye_im_not_drugged_out", "ye_i_took_seven",
                "ye_i_was_drugged_out", "ye_i_went_from_2_to_7", "ye_obama_was_our_opioids", "ye_opioids", "ye_seven_seven"};
        LinearLayout linearLayout = findViewById(R.id.buttonlayout);
        String id = "";
        for(int i = 0; i < filenames.length; i++) {
                if (!id.equals(filenames[i].substring(0,2))){
                    id = filenames[i].substring(0,2);
                    ImageView iv = new ImageView(this);
                    iv.setScaleType(ImageView.ScaleType.FIT_XY);
                    iv.setAdjustViewBounds(true);
                    iv.setImageResource(getResources().getIdentifier(id, "drawable", getPackageName()));
                    if (!id.equals("ez")) {
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

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (item.getItemId() == R.id.action_reset) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
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
