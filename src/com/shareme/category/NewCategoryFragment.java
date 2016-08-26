package com.shareme.category;

import java.lang.reflect.Field;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.shareme.R;
import com.shareme.tabpage.ViewPageAdapter;

public class NewCategoryFragment extends SherlockFragment {

	  private String phoneNum;
	  private String fragmentValue;
	  private Bundle bundle;

	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.viewpager_main, container, false);
	    //get the phone num
	    bundle = this.getArguments(); 
        if(bundle != null)
        {
            setPhoneNum(bundle.getString("phoneNum"));
            setFragmentValue(bundle.getString("myFragmentValue"));
            
        }
	    
	    // Locate the ViewPager in viewpager_main.xml
	    ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
	    // Set the ViewPagerAdapter into ViewPager
	    mViewPager.setAdapter(new ViewPageAdapter(getChildFragmentManager(),getPhoneNum(),getFragmentValue()));
	    return view;
	  }

	  @Override
	  public void onDetach() {
	    super.onDetach();
	    try {
	      Field childFragmentManager = Fragment.class
	          .getDeclaredField("mChildFragmentManager");
	      childFragmentManager.setAccessible(true);
	      childFragmentManager.set(this, null);
	    } catch (NoSuchFieldException e) {
	      throw new RuntimeException(e);
	    } catch (IllegalAccessException e) {
	      throw new RuntimeException(e);
	    }
	  }

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getFragmentValue() {
		return fragmentValue;
	}

	public void setFragmentValue(String fragmentValue) {
		this.fragmentValue = fragmentValue;
	}
	  
	  
	
}
