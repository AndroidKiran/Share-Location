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

public class GamesFragment extends SherlockFragment {
	
	private View mCategoryGameView;
	private SherlockFragmentActivity mContext;
	private Resources mResource;
	private ListView mGameListView;
	private List<CategoryDataBean> categoryDataBeanList;
	private List<CategoryDataBean> categoryDataBeanList1;
	private Bundle bundle;
	private String phoneNum;
	private String fragmentValue;
	private Button button_callFriend;
	
	private static String GAME_FRAGMENT_TAG = "Games";

	public GamesFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        mCategoryGameView = inflater.inflate(R.layout.fragment_games, container, false);
        mContext = getSherlockActivity();
        mResource = getResources();
        
        bundle = this.getArguments(); 
        if(bundle != null)
        {
            setPhoneNum(bundle.getString("phoneNum"));
            setFragmentValue(bundle.getString("fragmentValue"));
            Log.i("games frag num", getPhoneNum());
            Log.i("games frag value", getFragmentValue());
            
        }
        
        button_callFriend = (Button)mCategoryGameView.findViewById(R.id.button_CallFriend);
        if (getFragmentValue().equals("myCollection")) {
			button_callFriend.setVisibility(View.GONE);
		}else{
			button_callFriend.setVisibility(View.VISIBLE);
		}
        
        mGameListView = (ListView) mCategoryGameView.findViewById(R.id.gameListView);
        
        new FetchMovieData().execute();
        return mCategoryGameView;
    }
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		setMenuVisibility(true);
	}

	
	private List<CategoryDataBean> myMovieList(){
		
		CategoryDataBean categoryDataBean = new CategoryDataBean("Castlevania", "Gaint Leaper with hacking sword", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		CategoryDataBean categoryDataBean1 = new CategoryDataBean("Prince of persia", "The Legend is ALive", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		CategoryDataBean categoryDataBean2 = new CategoryDataBean("Metal Gear Solid", "Sword Speaks More Than Words", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		CategoryDataBean categoryDataBean3 = new CategoryDataBean("NFS", "Catch Me If You Can...!", Integer.valueOf("45"), Integer.valueOf("4"), Integer.valueOf("10"));
		
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
				CategoryDataAdapter myCategoryDataAdapter = (CategoryDataAdapter) mGameListView.getAdapter();
				if (myCategoryDataAdapter != null) {
					myCategoryDataAdapter.clear();
				}
				
				mCategoryDataAdpter = new CategoryDataAdapter(mContext, categoryDataBeanList1,GAME_FRAGMENT_TAG,getFragmentValue());
				mCategoryDataAdpter.notifyDataSetChanged();
				mGameListView.setAdapter(mCategoryDataAdpter);
				mGameListView.invalidateViews();
			} else {
				mGameListView.setAdapter(null);
				mGameListView.invalidateViews();
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
