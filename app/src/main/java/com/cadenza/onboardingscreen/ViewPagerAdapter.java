package com.cadenza.onboardingscreen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    int images[] ={
            R.drawable.onboard_img1,
            R.drawable.onboard_img2,
    };

    int title[] = {
        R.string.heading_one,
        R.string.heading_two
    };

    int descrption[] = {
            R.string.desc_one,
            R.string.desc_two
    };



    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


        View view = layoutInflater.inflate(R.layout.layout_slider,container,false);

        ImageView sliderImage = (ImageView) view.findViewById(R.id.titleimage);
        TextView sliderHead = (TextView) view.findViewById(R.id.textTitle);
        TextView sliderDescription = (TextView) view.findViewById(R.id.textDescription);

        sliderImage.setImageResource(images[position]);
        sliderHead.setText(title[position]);
        sliderDescription.setText(descrption[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
