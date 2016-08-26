package com.shareme.drawer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.shareme.R;
import com.shareme.adpater.MyContactAdapter;
import com.shareme.adpater.NavDrawerListAdapter;
import com.shareme.category.NewCategoryFragment;
import com.shareme.category.fragments.UploadFragment;
import com.shareme.model.ContactBean;
import com.shareme.model.NavDrawerItem;




public class ShareMeDrawerMainActivity extends SherlockFragmentActivity{
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	private ListView mDrawerContactList;
	
	/* Async task */
	private ProgressDialog pDialog;
	private Resources resource;
	private List<ContactBean> contactBeanList;
	private ContactBean contactBean;
	private MyContactAdapter mContactAdapter;
	
	private ActionBar mActionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();
		
		resource = getResources();
		mActionBar = getSupportActionBar();
		
		// enabling action bar app icon and behaving it as toggle button
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);
		
		
		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		
		// nav drawer icons from resources
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		mDrawerContactList = (ListView) findViewById(R.id.list_contactslidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// My Profile 
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// My Collection
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));

		// Recycle the typed array
		navMenuIcons.recycle();
		
		contactBeanList = new ArrayList<ContactBean>();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		mDrawerContactList.setOnItemClickListener(new ContactSlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),navDrawerItems);
		mDrawerList.setAdapter(adapter);
		//mDrawerContactList.setAdapter(adapter);

		

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				mActionBar.setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				mActionBar.setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
		
		
		new ListViewContactsLoader().execute();
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}
	
	/**
	 * Slide menu item click listener
	 * */
	private class ContactSlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			contactDisplayView(position);
		}
	}
	

	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.share_flash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			if(mDrawerLayout.isDrawerOpen(Gravity.END))
			{
                mDrawerLayout.closeDrawer(Gravity.END);
                
			}else{
				 mDrawerLayout.openDrawer(Gravity.END);
				 if(mDrawerLayout.isDrawerOpen(Gravity.START))
					{
		                mDrawerLayout.closeDrawer(Gravity.START);
					}
			}
			return true;
			
		case android.R.id.home:
			
			if(mDrawerLayout.isDrawerOpen(Gravity.START))
			{
                mDrawerLayout.closeDrawer(Gravity.START);
			}else{
				 mDrawerLayout.openDrawer(Gravity.START);
					if(mDrawerLayout.isDrawerOpen(Gravity.END))
					{
		                mDrawerLayout.closeDrawer(Gravity.END);
		                
					}
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		SherlockFragment fragment = null;
		switch (position) {
		case 0:
			fragment = new UploadFragment();
			break;
		case 1:
			fragment = new NewCategoryFragment();
			
			Bundle b = new Bundle();
			b.putString("phoneNum",contactBeanList.get(position).getMobileNum());
			b.putString("myFragmentValue", "myCollection");
			fragment.setArguments(b);
			
			break;

		default:
			break;
		}

		if (fragment != null) {
			mActionBar.removeAllTabs();
			FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
			fragmentTransaction.replace(R.id.frame_container, fragment);
			//fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
	        
			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
		
	}
	
	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void contactDisplayView(int position) {
		SherlockFragment fragment = null;
		fragment = new NewCategoryFragment();
		
		if (fragment != null) {
			mActionBar.removeAllTabs();
			FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
			fragmentTransaction.replace(R.id.frame_container, fragment);
			//fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
			
			Bundle b = new Bundle();
			b.putString("phoneNum",contactBeanList.get(position).getMobileNum());
			b.putString("myFragmentValue", "friendCollection");
			fragment.setArguments(b);
			
			// update selected item and title, then close the drawer
			mDrawerContactList.setItemChecked(position, true);
			mDrawerContactList.setSelection(position);
			setTitle(contactBeanList.get(position).getUserName());
			mDrawerLayout.closeDrawer(mDrawerContactList);
			
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		mActionBar.setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	  public void onBackPressed() {

	    FragmentManager manager = getSupportFragmentManager();
	    if (manager.getBackStackEntryCount() > 0) {
	      // If there are back-stack entries, leave the FragmentActivity
	      // implementation take care of them.
	      manager.popBackStack();

	    } else {
	      // Otherwise, ask user if he wants to leave :) 
	      super.onBackPressed();
	    }
	  }
	
	/** An AsyncTask class to retrieve and load listview with contacts */
    private class ListViewContactsLoader extends AsyncTask<Void, Void, List<ContactBean>>{ 
    	
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ShareMeDrawerMainActivity.this);
			pDialog.setMessage(resource.getString(R.string.loading_msg));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		@Override
		protected List<ContactBean> doInBackground(Void... params) {
			Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
			
			// Querying the table ContactsContract.Contacts to retrieve all the contacts
			Cursor contactsCursor = getContentResolver().query(contactsUri, null, null, null, 
									ContactsContract.Contacts.DISPLAY_NAME + " ASC ");
			
			if(contactsCursor.moveToFirst()){
				do{
					long contactId = contactsCursor.getLong(contactsCursor.getColumnIndex("_ID"));
					
					Log.i(".............", "....................");
					Uri dataUri = ContactsContract.Data.CONTENT_URI;
					
					// Querying the table ContactsContract.Data to retrieve individual items like
					// home phone, mobile phone, work email etc corresponding to each contact 
					Cursor dataCursor = getContentResolver().query(dataUri, null, 
											ContactsContract.Data.CONTACT_ID + "=" + contactId, 
											null, null);
					
					
					String displayName = "";
					String mobilePhone = "";
					
					Uri photoPath = null; 
					byte[] photoByte = null;
					
					
					
					if(dataCursor.moveToFirst()){
						// Getting Display Name
						displayName = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME ));
						do{
							
							// Getting Phone numbers
							if(dataCursor.getString(dataCursor.getColumnIndex("mimetype")).equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)){
								switch(dataCursor.getInt(dataCursor.getColumnIndex("data2"))){
									case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE : 
										mobilePhone = dataCursor.getString(dataCursor.getColumnIndex("data1"));
										break;
								}
							}
							
								
							// Getting Photo	
							if(dataCursor.getString(dataCursor.getColumnIndex("mimetype")).equals(ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)){								
								photoByte = dataCursor.getBlob(dataCursor.getColumnIndex("data15"));
								
								if(photoByte != null) {							
									Bitmap bitmap = BitmapFactory.decodeByteArray(photoByte, 0, photoByte.length);
									
									// Getting Caching directory 
				                    File cacheDirectory = getBaseContext().getCacheDir();
	
				                    // Temporary file to store the contact image 
				                    File tmpFile = new File(cacheDirectory.getPath() + "/wpta_"+contactId+".png");
	
				                    // The FileOutputStream to the temporary file
				                    try {
										FileOutputStream fOutStream = new FileOutputStream(tmpFile);
										
										// Writing the bitmap to the temporary file as png file
					                    bitmap.compress(Bitmap.CompressFormat.PNG,100, fOutStream);
	
					                    // Flush the FileOutputStream
					                    fOutStream.flush();
	
					                    //Close the FileOutputStream
					                    fOutStream.close();
	
									} catch (Exception e) {
										e.printStackTrace();
									}
				                    
				                    photoPath = Uri.fromFile(tmpFile);
								}
								
							}
							
						}while(dataCursor.moveToNext());					
						
						String details = "";
						
						// Concatenating various information to single string
						
					
						if(mobilePhone != null && !mobilePhone.equals("") )
							details = mobilePhone;
						
						contactBean = new ContactBean(String.valueOf(contactId), displayName, details, photoPath);
						contactBeanList.add(contactBean);
					}
					
				}while(contactsCursor.moveToNext());
			}
			return contactBeanList;
		}
    	
		@Override
		protected void onPostExecute(List<ContactBean> myContactList) {			
			pDialog.dismiss();
		
				if (myContactList.size() != 0) {
					MyContactAdapter myContactAdapter = (MyContactAdapter)mDrawerContactList.getAdapter();
					if(myContactAdapter != null){
						myContactAdapter.clear();
						}
					mContactAdapter = new MyContactAdapter(ShareMeDrawerMainActivity.this, myContactList);
					mContactAdapter.notifyDataSetChanged();
					mDrawerContactList.setAdapter(mContactAdapter);
					mDrawerContactList.invalidateViews();
					
				}else{
					mDrawerContactList.setAdapter(null);
					mDrawerContactList.invalidateViews();
					
					//alertDialogManager.showAlertDialog(mContext, "ERROR",resource.getString(R.string.no_record_found_error_msg) ,false);
				}
			}
		}
	
  }
	

