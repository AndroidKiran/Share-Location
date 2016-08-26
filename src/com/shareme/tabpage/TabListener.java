package com.shareme.tabpage;


import android.app.Activity;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;




public class TabListener<T extends SherlockFragment> implements com.actionbarsherlock.app.ActionBar.TabListener
{
   private SherlockFragment mFragment;
   private final SherlockFragmentActivity mActivity;
   private final String mTag;
   private final Class<T> mClass;

   public TabListener(Activity activity, String tag, Class<T> clz)
   {
      mActivity = (SherlockFragmentActivity) activity;
      mTag = tag;
      mClass = clz;
   }


   public void onTabSelected(Tab tab, FragmentTransaction ft)
   {
      // Check if the fragment has already been initialised
      SherlockFragment  preInitializedFragment = (SherlockFragment) mActivity.getSupportFragmentManager().findFragmentByTag(mTag);
      if (mFragment != null)
      {
         // If it exists, simply attach it in order to show it
         ft.attach(mFragment);
      }
      else if (preInitializedFragment != null)
      {
         mFragment = preInitializedFragment;
         ft.attach(mFragment);
      }
      else
      {
         // Not found, so instantiate and add it to the activity
         mFragment = (SherlockFragment) SherlockFragment.instantiate(mActivity, mClass.getName());
         ft.add(android.R.id.content, mFragment, mTag);
      }
   }


   public void onTabUnselected(Tab tab, FragmentTransaction ft)
   {
      if (mFragment != null) {
         // Detach the fragment, because another one is being attached
         ft.detach(mFragment);
      }
   }


   public void onTabReselected(Tab tab, FragmentTransaction ft)
   {
      // User selected the already selected tab. Usually do nothing.
   }
}