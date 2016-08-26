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

public class SeriesFragment extends SherlockFragment {
	
	private View mCategorySeriesView;
	private SherlockFragmentActivity mContext;
	private Resources mResource;
	private List<CategoryDataBean> categoryDataBeanList;
	private List<CategoryDataBean> categoryDataBeanList1;
	private ListView mSeriesListView;
	private String phoneNum;
	private String fragmentValue;
	private Bundle bundle;
	private Button button_callFriend;
	
	private static String SERIES_FRAGMENT_TAG = "Series";

	public SeriesFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        mCategorySeriesView = inflater.inflate(R.layout.fragment_series, container, false);
        mContext = getSherlockActivity();
        mResource = getResources();
        
        bundle = this.getArguments(); 
        if(bundle != null)
        {
            setPhoneNum(bundle.getString("phoneNum"));
            setFragmentValue(bundle.getString("fragmentValue"));
            Log.i("series frag num", getPhoneNum());
            Log.i("series frag value", getFragmentValue());
            
        }
        
        button_callFriend = (Button)mCategorySeriesView.findViewById(R.id.button_CallFriend);
        if (getFragmentValue().equals("myCollection")) {
			button_callFriend.setVisibility(View.GONE);
		}else{
			button_callFriend.setVisibility(View.VISIBLE);
		}
        
        mSeriesListView = (ListView) mCategorySeriesView.findViewById(R.id.seriesListView);
        new FetchWebServiceData().execute();
        return mCategorySeriesView;
    }
	
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		setMenuVisibility(true);
	}

	
	private List<CategoryDataBean> mySeriesList(){
		
		CategoryDataBean categoryDataBean = new CategoryDataBean("One And HalfMan", "Owesome , Keep watching", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		CategoryDataBean categoryDataBean1 = new CategoryDataBean("Prison break season 2", "One More Hope Arises", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		CategoryDataBean categoryDataBean2 = new CategoryDataBean("Flash Forwrd Season 1", "Yeeah Keep reveal", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		CategoryDataBean categoryDataBean3 = new CategoryDataBean("Games Of Throne COmplete", "Will Throne get to snidor??", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		
		categoryDataBeanList = new ArrayList<CategoryDataBean>();
		categoryDataBeanList.add(categoryDataBean);
		categoryDataBeanList.add(categoryDataBean1);
		categoryDataBeanList.add(categoryDataBean2);
		categoryDataBeanList.add(categoryDataBean3);
		
		return categoryDataBeanList;
		
	}
	
	
	private class FetchWebServiceData extends AsyncTask<Void, Void, List<CategoryDataBean>> {

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
			setCategoryDataBeanList1(mySeriesList());
			return getCategoryDataBeanList1();
		}

		@Override
		protected void onPostExecute(List<CategoryDataBean> resultList) {
			super.onPostExecute(resultList);
			pDialog.dismiss();
			
			if (resultList.size() != 0) {
				CategoryDataAdapter myCategoryDataAdapter = (CategoryDataAdapter) mSeriesListView.getAdapter();
				if (myCategoryDataAdapter != null) {
					myCategoryDataAdapter.clear();
				}
				
				mCategoryDataAdpter = new CategoryDataAdapter(mContext, categoryDataBeanList1,SERIES_FRAGMENT_TAG,getFragmentValue());
				mCategoryDataAdpter.notifyDataSetChanged();
				mSeriesListView.setAdapter(mCategoryDataAdpter);
				mSeriesListView.invalidateViews();
				
			} else {
				mSeriesListView.setAdapter(null);
				mSeriesListView.invalidateViews();
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
