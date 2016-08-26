package com.shareme.category.fragments;


import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.shareme.R;
import com.shareme.adpater.CategoryDataAdapter;
import com.shareme.model.CategoryDataBean;
import com.shareme.myinterfaces.DeleteRow;

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

public class MovieFragment extends SherlockFragment implements DeleteRow {
	List<CategoryDataBean> categoryDataBeanList;
	List<CategoryDataBean> categoryDataBeanList1;
	private Resources mResource;
	private SherlockFragmentActivity mContext;
	private View mCategoryMovieView;
	private ListView mMovieListView;
	private Bundle bundle;
	private String phoneNum;
	private String fragmentValue;
	private Button button_callFriend;
	
	public MovieFragment(){}
	private static String MOVIE_FRAGMENT_TAG = "Movies";
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        mCategoryMovieView = inflater.inflate(R.layout.fragment_movie, container, false);
        mContext = getSherlockActivity();
        mResource = getResources();
        
        bundle = this.getArguments(); 
        if(bundle != null)
        {
            setPhoneNum(bundle.getString("phoneNum"));
            setFragmentValue(bundle.getString("fragmentValue"));
            Log.i("movie frag num", getPhoneNum());
            Log.i("movie frag value", getFragmentValue());
            
        }
        
        button_callFriend = (Button)mCategoryMovieView.findViewById(R.id.button_CallFriend);
        if (getFragmentValue().equals("myCollection")) {
			button_callFriend.setVisibility(View.GONE);
		}else{
			button_callFriend.setVisibility(View.VISIBLE);
		}
        
        mMovieListView = (ListView) mCategoryMovieView.findViewById(R.id.movieListView);
        
        new FetchMovieData().execute();
        return mCategoryMovieView;
    }
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		setMenuVisibility(true);
	}

	
	private List<CategoryDataBean> myMovieList(){
		
		CategoryDataBean categoryDataBean = new CategoryDataBean("Titanic_Bdrip", "Romantic movie", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		CategoryDataBean categoryDataBean1 = new CategoryDataBean("Rock", "Action movie", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		CategoryDataBean categoryDataBean2 = new CategoryDataBean("Ganga_dvdrip", "Story movie", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		CategoryDataBean categoryDataBean3 = new CategoryDataBean("300_camrip", "Romantic movie", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		
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
			setCategoryDataBeanList1(myMovieList());
			return getCategoryDataBeanList1();
		}

		@Override
		protected void onPostExecute(List<CategoryDataBean> resultList) {
			super.onPostExecute(resultList);
			pDialog.dismiss();
			
			if (resultList.size() != 0) {
				CategoryDataAdapter myCategoryDataAdapter = (CategoryDataAdapter) mMovieListView.getAdapter();
				if (myCategoryDataAdapter != null) {
					myCategoryDataAdapter.clear();
				}
				
				mCategoryDataAdpter = new CategoryDataAdapter(mContext, categoryDataBeanList1,MOVIE_FRAGMENT_TAG,getFragmentValue());
				mCategoryDataAdpter.notifyDataSetChanged();
				mMovieListView.setAdapter(mCategoryDataAdpter);
				mMovieListView.invalidateViews();
			} else {
				mMovieListView.setAdapter(null);
				mMovieListView.invalidateViews();
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

	@Override
	public void deleteCategoryRow(int position) {
		
		categoryDataBeanList.remove(position);
		mMovieListView.invalidateViews();
		
	}
	
	
	
}
