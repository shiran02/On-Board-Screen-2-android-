package com.cadenza.onboardingscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPager mSliderViewPager;
    LinearLayout mDotLayout;
    Button nextBtn,backBtn,skipBtn;

    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;

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
    }
}