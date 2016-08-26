package com.shareme.category;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.shareme.R;
import com.shareme.tabpage.TabsPagerAdapter;

public class CategoryFragment extends SherlockFragment implements ActionBar.TabListener{
	
	private View categoryView;
	private ViewPager viewPager;
	private SherlockFragmentActivity mContext;
	private ActionBar mActionBar;
	
	private String[] tabs = { "Hindi", "Enlisg", "Kannnad" };
	private TabsPagerAdapter mCategoryAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

			categoryView = inflater.inflate(R.layout.viewpager_main, container, false);
			mContext = getSherlockActivity();
			mActionBar = mContext.getSupportActionBar();
			mActionBar.setDisplayHomeAsUpEnabled(true);
			mActionBar.setHomeButtonEnabled(true);
			mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			
			// Initilization
			viewPager = (ViewPager) categoryView.findViewById(R.id.viewPager);
			mCategoryAdapter = new TabsPagerAdapter(getChildFragmentManager());

			viewPager.setAdapter(mCategoryAdapter);
						

			// Adding Tabs
			for (String tab_name : tabs) {
				mActionBar.addTab(mActionBar.newTab().setText(tab_name).setTabListener(this));
			}

			/**
			 * on swiping the viewpager make respective tab selected
			 * */
			viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int position){
				// on changing the page
				// make respected tab selected
				mActionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});		
		
		return categoryView;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	 
}

