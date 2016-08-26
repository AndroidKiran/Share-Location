package com.shareme.adpater;

import com.shareme.R;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CustomPagerAdapter extends PagerAdapter {

    private Context context;
    // public CustomPagerAdapter(Context context, Vector<View> pages) {
    // this.context = context;
    // this.pages = pages;
    // }

    public CustomPagerAdapter(Context context) {
        this.context = context;
        
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater.from(container.getContext()));
        // View page = pages.get(position);
        View view = null;
       
            view = inflater.inflate(R.layout.activity_main, null);
            
            ((ViewPager) container).addView(view);

      
        return view;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
    	
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}