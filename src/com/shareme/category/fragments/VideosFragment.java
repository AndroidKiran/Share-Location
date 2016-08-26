package com.shareme.category.fragments;

import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.shareme.R;
import com.shareme.adpater.CategoryDataAdapter;
import com.shareme.model.CategoryDataBean;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class VideosFragment extends SherlockFragment {
	
	private View mVideoCategoryView;
	private SherlockFragmentActivity mContext;
	private Resources mResource;
	private ListView mVideoListView;
	private List<CategoryDataBean> categoryDataBeanList;
	private List<CategoryDataBean> categoryDataBeanList1;
	
	private String phoneNum;
	private String fragmentValue;
	private Bundle bundle;
	private Button button_callFriend;
	
	private static String VIDEO_FRAGMENT_TAG = "Videos";

	public VideosFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        mVideoCategoryView = inflater.inflate(R.layout.fragment_videos, container, false);
        mContext = getSherlockActivity();
        mResource = getResources();
        
        bundle = this.getArguments(); 
        if(bundle != null)
        {
            setPhoneNum(bundle.getString("phoneNum"));
            setFragmentValue(bundle.getString("fragmentValue"));
            Log.i("video frag num", getPhoneNum());
            Log.i("video frag value", getFragmentValue());
            
        }
        
        button_callFriend = (Button) mVideoCategoryView.findViewById(R.id.button_CallFriend);
        if (getFragmentValue().equals("myCollection")) {
			button_callFriend.setVisibility(View.GONE);
		}else{
			button_callFriend.setVisibility(View.VISIBLE);
		}
        
        mVideoListView = (ListView) mVideoCategoryView.findViewById(R.id.videoListView);
        
        new FetchMovieData().execute();
        return mVideoCategoryView;
    }
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		setMenuVisibility(true);
	}

	
	private List<CategoryDataBean> myVideoList(){
		
		CategoryDataBean categoryDataBean = new CategoryDataBean("Brayn Adam", "GONE...GONE....", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		CategoryDataBean categoryDataBean1 = new CategoryDataBean("SUMER of 69", "Reminds me of college", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		CategoryDataBean categoryDataBean2 = new CategoryDataBean("Sathia sathia...!", "All time favourite..!", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		CategoryDataBean categoryDataBean3 = new CategoryDataBean("Geela Geela", "Everything is wet...", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		
		categoryDataBeanList = new ArrayList<CategoryDataBean>();
		categoryDataBeanList.add(categoryDataBean);
		categoryDataBeanList.add(categoryDataBean1);
		categoryDataBeanList.add(categoryDataBean2);
		categoryDataBeanList.add(categoryDataBean3);
		
		return categoryDataBeanList;
		
	}
	
	private class FetchMovieData extends AsyncTask<Void, Void, List<CategoryDataBean>> {

		private ProgressDialog pDialog;
		private CategoryDataAdapter mCategoryDataAdpter;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(mContext);
			pDialog.setMessage(mResource.getString(R.string.loading_msg));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			
		}

		@Override
		protected List<CategoryDataBean> doInBackground(Void... params) {
			setCategoryDataBeanList1(myVideoList());
			return getCategoryDataBeanList1();
		}

		@Override
		protected void onPostExecute(List<CategoryDataBean> resultList) {
			super.onPostExecute(resultList);
			pDialog.dismiss();
			
			if (resultList.size() != 0) {
				CategoryDataAdapter myCategoryDataAdapter = (CategoryDataAdapter) mVideoListView.getAdapter();
				if (myCategoryDataAdapter != null) {
					myCategoryDataAdapter.clear();
				}
				
				mCategoryDataAdpter = new CategoryDataAdapter(mContext, getCategoryDataBeanList1(),VIDEO_FRAGMENT_TAG,getFragmentValue());
				mCategoryDataAdpter.notifyDataSetChanged();
				mVideoListView.setAdapter(mCategoryDataAdpter);
				mVideoListView.invalidateViews();
				
			} else {
				
				mVideoListView.setAdapter(null);
				mVideoListView.invalidateViews();
			}
		}
	}


	public List<CategoryDataBean> getCategoryDataBeanList1() {
		return categoryDataBeanList1;
	}

	public void setCategoryDataBeanList1(
			List<CategoryDataBean> categoryDataBeanList1) {
		this.categoryDataBeanList1 = categoryDataBeanList1;
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
