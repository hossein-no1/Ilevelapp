package com.example.pneumatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class SplashScreen extends AppCompatActivity {

    Context context = this;
    WifiManager wifiManager;
    LottieAnimationView lottieAnim;
    ImageView imgFlashUp,imgFlashDown;
    MediaPlayer mpWindSound;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //find view by id
        findViewIds();

        //set animation for logo
        Animation animFlashUp= AnimationUtils.loadAnimation(context,R.anim.anim_flash_up_splash_screen_activity);
        Animation animFlashDown= AnimationUtils.loadAnimation(context,R.anim.anim_flash_down_splash_screen_activity);
        imgFlashUp.setAnimation(animFlashUp);
        imgFlashDown.setAnimation(animFlashDown);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mpWindSound.start();
            }
        },500);



        //go to next activity after 2.5 second
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                boolean wifiState=wifiManager.isWifiEnabled();
                if (wifiState==true)
                {
                        startActivity(new Intent(context, MainActivity.class));
                        finish();
                }
                else
                {
                    turnOnWifi();
                }

            }
        }, 2500);


    }

    private void findViewIds() {

        //find view all items
        lottieAnim=(LottieAnimationView)findViewById(R.id.lottieAnimMainSSA);
        wifiManager=(WifiManager) context.getSystemService(context.WIFI_SERVICE);
        imgFlashUp=(ImageView)findViewById(R.id.imgFlashUpSSA);
        imgFlashDown=(ImageView)findViewById(R.id.imgFlashDownSSA);

        //find wind sound of raw folder
        mpWindSound=MediaPlayer.create(context,R.raw.wind_sound);

    }


    private void turnOnWifi()
    {
        lottieAnim.setVisibility(View.GONE);
        Snackbar.make(findViewById(android.R.id.content),"Your wifi is off",BaseTransientBottomBar.LENGTH_INDEFINITE)
                .setAction("ON", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wifiManager.setWifiEnabled(true);
                        startActivity(new Intent(context,MainActivity.class));
                        finish();
                    }
                }).show();
    }


}
