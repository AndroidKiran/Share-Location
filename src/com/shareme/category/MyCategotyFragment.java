package com.shareme.category;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.shareme.category.fragments.GamesFragment;
import com.shareme.category.fragments.MovieFragment;
import com.shareme.tabpage.TabListener;

public class MyCategotyFragment extends SherlockFragment{
	private static final String SEARCH_BY_NAME = "Names";
    private static final String SEARCH_BY_DISTANCE = "Distance";
	private ActionBar mActionBar;
	private SherlockFragmentActivity mContext;
	private Resources resources;
	
	 	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        mContext = getSherlockActivity();
	        mActionBar = mContext.getSupportActionBar();
	        mActionBar.setDisplayShowTitleEnabled(true);
	        mActionBar.setDisplayHomeAsUpEnabled(true);
	        
	        resources = getResources();
	        setupTabs(savedInstanceState);
	
	 	}
	 	
	 	
	    private void setupTabs(Bundle savedInstanceState) {
	    	
	    	mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	        Tab tab = mActionBar.newTab().setText(SEARCH_BY_NAME).setTabListener(new TabListener<GamesFragment>(mContext, SEARCH_BY_NAME, GamesFragment.class));
	        mActionBar.addTab(tab);

	        tab = mActionBar.newTab().setText(SEARCH_BY_DISTANCE).setTabListener(new TabListener<MovieFragment>(mContext, SEARCH_BY_DISTANCE, MovieFragment.class));
	        mActionBar.addTab(tab);

	        if (savedInstanceState != null) {
	        	mActionBar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
	        }
	    }

	    @Override
		public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("tab", mActionBar.getSelectedNavigationIndex());
	    }
	    
		@Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
		}
}


