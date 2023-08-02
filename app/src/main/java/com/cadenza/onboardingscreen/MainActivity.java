package com.cadenza.onboardingscreen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPager mSliderViewPager;
    LinearLayout mDotLayout;
    Button nextBtn,backBtn,skipBtn;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextBtn =findViewById(R.id.nextBtn);
        backBtn =findViewById(R.id.backBtn);
        skipBtn =findViewById(R.id.skipBtn);

        mSliderViewPager = (ViewPager) findViewById(R.id.sliderViewPager);
        mDotLayout =(LinearLayout) findViewById(R.id.indicater_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);
        mSliderViewPager.setAdapter(viewPagerAdapter);



        // Check if the onboarding screen has been shown before
        if (!isOnboardingScreenShown()) {
            // If not shown before, display the onboarding screen
            showOnboardingScreen();
        } else {
            // If onboarding screen has been shown before, show the MainActivity
            showMainActivity();
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getItem(0)> 0){
                    mSliderViewPager.setCurrentItem(getItem(-1),true);
                }
            }
        });



        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getItem(0)< 1){
                    mSliderViewPager.setCurrentItem(getItem(1),true);
                }else{
                    Intent i = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(i);
                    finish();
                }
            }
        });


        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(i);
                finish();
            }
        });

        setupIndicater(0);
        mSliderViewPager.addOnPageChangeListener(viewListner);



    }



    private void showOnboardingScreen() {
        setContentView(R.layout.activity_main);

        // ... (rest of your existing code for onboarding screen)

        // Set a flag in SharedPreferences to mark the onboarding screen as shown
        SharedPreferences sharedPreferences = getSharedPreferences("com.cadenza.onboardingscreen", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("onboardingShown", true);
        editor.apply();
    }

    private void showMainActivity() {
        Intent i = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(i);
        finish();
    }

    private boolean isOnboardingScreenShown() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.cadenza.onboardingscreen", MODE_PRIVATE);
        return sharedPreferences.getBoolean("onboardingShown", false);
    }









    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setupIndicater(int position){

        dots = new TextView[2];
        mDotLayout.removeAllViews();

        for(int i=0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
        }
    }


    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {
            setupIndicater(position);

            if(position > 0){
                backBtn.setVisibility(View.VISIBLE);
            }else{
                backBtn.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    private int getItem(int i){
        return mSliderViewPager.getCurrentItem() + i;
    }

}