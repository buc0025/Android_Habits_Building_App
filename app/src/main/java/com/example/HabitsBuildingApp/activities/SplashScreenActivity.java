package com.example.HabitsBuildingApp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.HabitsBuildingApp.R;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen easySplashScreen = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#99D9EA"))
                .withFooterText("Created by Stanley")
                .withLogo(R.drawable.habits_logo);

        easySplashScreen.getFooterTextView().setTextColor(Color.WHITE);
        easySplashScreen.getFooterTextView().setTextSize(20);

        View splashScreen = easySplashScreen.create();
        setContentView(splashScreen);
    }
}