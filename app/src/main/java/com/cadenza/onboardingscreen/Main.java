package com.cadenza.onboardingscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Check if the onboarding screen has been shown before
        if (!isOnboardingScreenShown()) {
            // If not shown before, display the onboarding screen
            showOnboardingScreen();
        } else {
            // If onboarding screen has been shown before, show the MainActivity2
            showMainActivity2();
        }


    }

    private void showOnboardingScreen() {
        setContentView(R.layout.activity_main);

        // Find your onboarding screen views and set up the necessary configurations

         //For example, you can initialize ViewPager, LinearLayout, and buttons:
//         ViewPager onboardingViewPager = findViewById(R.id.onboardingViewPager);
//         LinearLayout onboardingDotLayout = findViewById(R.id.onboarding_indicator_layout);
//         Button onboardingNextBtn = findViewById(R.id.onboarding_nextBtn);
//         Button onboardingSkipBtn = findViewById(R.id.onboarding_skipBtn);

        // ... (rest of your code to set up the onboarding screens)

        // Set a flag in SharedPreferences to mark the onboarding screen as shown
        SharedPreferences sharedPreferences = getSharedPreferences("com.cadenza.onboardingscreen", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("onboardingShown", true);
        editor.apply();
    }

    private void showMainActivity2() {
        Intent i = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(i);
        finish();
    }

    private boolean isOnboardingScreenShown() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.cadenza.onboardingscreen", MODE_PRIVATE);
        return sharedPreferences.getBoolean("onboardingShown", false);
    }

}