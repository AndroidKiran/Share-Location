package com.shareme.tabpage;

import com.shareme.category.fragments.AudioFragment;
import com.shareme.category.fragments.GamesFragment;
import com.shareme.category.fragments.MovieFragment;
import com.shareme.category.fragments.SeriesFragment;
import com.shareme.category.fragments.VideosFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class ViewPageAdapter extends FragmentPagerAdapter {


	// Declare the number of ViewPager pages
	  final int PAGE_COUNT = 5;
	  private String titles[] = new String[] { "Movies", "Games" ,"Series" ,"Videos" ,"Audio" };
	  private String phoneNum;
	  private Bundle b;
	  private String fragmentValue;

	  public ViewPageAdapter(FragmentManager fm,String phoneNum,String fragmentValue) {
		super(fm);
		this.phoneNum = phoneNum;
		this.fragmentValue = fragmentValue;
	  }
	  
	  @Override
	  public Fragment getItem(int position) {
	    switch (position) {

	      // Open FragmentTab1.java
	    case 0:
		      MovieFragment fragmenttab1 = new MovieFragment();
		      
		      b = new Bundle();
		      b.putString("phoneNum",phoneNum);
		      b.putString("fragmentValue",fragmentValue);
		      fragmenttab1.setArguments(b);
		      
		      return fragmenttab1;

	      // Open FragmentTab2.java
	    case 1:
		      GamesFragment fragmenttab2 = new GamesFragment();
		      b = new Bundle();
		      b.putString("phoneNum",phoneNum);
		      b.putString("fragmentValue",fragmentValue);
		      fragmenttab2.setArguments(b);
		      
		      return fragmenttab2;
	      
	    case 2:
		      SeriesFragment fragmenttab3 = new SeriesFragment();
		      
		      b = new Bundle();
		      b.putString("phoneNum",phoneNum);
		      b.putString("fragmentValue",fragmentValue);
		      fragmenttab3.setArguments(b);
		      
		      return fragmenttab3;
		      
	    case 3:
		      VideosFragment fragmenttab4 = new VideosFragment();
		      
		      b = new Bundle();
		      b.putString("phoneNum",phoneNum);
		      b.putString("fragmentValue",fragmentValue);
		      fragmenttab4.setArguments(b);
		      
		      return fragmenttab4;
		      
	    case 4:
		      AudioFragment fragmenttab5 = new AudioFragment();
		      
		      b = new Bundle();
		      b.putString("phoneNum",phoneNum);
		      b.putString("fragmentValue",fragmentValue);
		      fragmenttab5.setArguments(b);
		      
		      return fragmenttab5;
	    }
	    return null;
	  }

	  public CharSequence getPageTitle(int position) {
	    return titles[position];
	  }

	  @Override
	  public int getCount() {
	    return PAGE_COUNT;
	  }
}
