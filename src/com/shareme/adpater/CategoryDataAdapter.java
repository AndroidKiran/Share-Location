package com.shareme.adpater;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shareme.R;
import com.shareme.category.fragments.AudioFragment;
import com.shareme.category.fragments.GamesFragment;
import com.shareme.category.fragments.MovieFragment;
import com.shareme.category.fragments.SeriesFragment;
import com.shareme.category.fragments.VideosFragment;
import com.shareme.drawer.ShareMeDrawerMainActivity;
import com.shareme.model.CategoryDataBean;

public class CategoryDataAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mInflater;
	private List<CategoryDataBean> categoryDataBeanList;
	private ArrayList<CategoryDataBean> listOrigin;
	private ShareMeDrawerMainActivity myShareMeDrawerMainActivity;
	private Fragment fragment;
	private String fragmentTag;
	private MovieFragment mMovieFragment;
	private GamesFragment mGameFragment;
	private SeriesFragment mSeriesFragment;
	private VideosFragment mVideoFragment;
	private AudioFragment mAudioFragment;
	private String fragmentValue;

	public CategoryDataAdapter(Context context, List<CategoryDataBean> categoryDataBeanList, String fragmentTag,String fragmentValue) {
		mContext = context;
		this.categoryDataBeanList = categoryDataBeanList;
		mInflater = LayoutInflater.from(mContext);
		this.listOrigin = new ArrayList<CategoryDataBean>();
		this.listOrigin.addAll(categoryDataBeanList);
		this.fragmentTag = fragmentTag;
		this.fragmentValue = fragmentValue;
	}

	public class ViewHolder {		
		TextView categoryDataNameTV;
		TextView aboutCategoryDataTV;
		TextView goodDataTV;
		TextView badDataTV;
		TextView dataReviewTV;
		Button buttonThumbUp;
		Button buttonThumbDown;
		Button buttonViewCommment;
		Button buttonDelete;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = mInflater.inflate(R.layout.category_list_item, null);
			holder.categoryDataNameTV = (TextView) view.findViewById(R.id.categoryDataNameTextView);
			holder.aboutCategoryDataTV = (TextView) view.findViewById(R.id.aboutCategoryDataTextView);
			holder.goodDataTV = (TextView) view.findViewById(R.id.goodCategoryDataTextView);
			holder.badDataTV = (TextView) view.findViewById(R.id.badCategoryDataTextView);
			holder.dataReviewTV = (TextView) view.findViewById(R.id.categoryDataReviewTextView);
			
			holder.buttonThumbUp = (Button) view.findViewById(R.id.button_ThumbUp);
			holder.buttonThumbDown = (Button) view.findViewById(R.id.button_ThumbDown);
			holder.buttonViewCommment = (Button) view.findViewById(R.id.button_ViewComments);
			holder.buttonDelete = (Button) view.findViewById(R.id.button_Delete);
			
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		myShareMeDrawerMainActivity = ((ShareMeDrawerMainActivity)mContext);
		if (fragmentTag.equals("Movies")) {
			mMovieFragment = (MovieFragment)myShareMeDrawerMainActivity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
		}else if (fragmentTag.equals("Games")) {
			mGameFragment = (GamesFragment)myShareMeDrawerMainActivity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
		}else if (fragmentTag.equals("Series")) {
			mSeriesFragment = (SeriesFragment)myShareMeDrawerMainActivity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
		}else if (fragmentTag.equals("Videos")) {
			mVideoFragment = (VideosFragment)myShareMeDrawerMainActivity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
		}else if (fragmentTag.equals("Audio")) {
			mAudioFragment = (AudioFragment)myShareMeDrawerMainActivity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
		}

		
		holder.categoryDataNameTV.setText(categoryDataBeanList.get(position).getDataName());
		holder.aboutCategoryDataTV.setText(categoryDataBeanList.get(position).getAboutData());
		holder.goodDataTV.setText(String.valueOf(categoryDataBeanList.get(position).getGoodData()));
		holder.badDataTV.setText(String.valueOf(categoryDataBeanList.get(position).getBadData()));
		holder.dataReviewTV.setText(String.valueOf(categoryDataBeanList.get(position).getDataReview()));
		
		if (fragmentValue.equals("friendCollection")) {
			holder.buttonDelete.setVisibility(View.GONE);

			holder.buttonThumbUp.setOnClickListener(new OnClickListener() {
				
				private int goodRating = 0;

				@Override
				public void onClick(View v) {
					
					
					goodRating = Integer.parseInt(holder.goodDataTV.getText().toString());
					Toast.makeText(mContext, "clicking "+goodRating, Toast.LENGTH_SHORT).show();
					holder.goodDataTV.setText(String.valueOf(++goodRating));
					
				}
			});
			
			holder.buttonThumbDown.setOnClickListener(new OnClickListener() {
				
				private int badRating = 0;

				@Override
				public void onClick(View v) {
					Toast.makeText(mContext, "clicking", Toast.LENGTH_SHORT).show();
					badRating = Integer.parseInt(holder.badDataTV.getText().toString());
					holder.badDataTV.setText(String.valueOf(--badRating));
				}
			});
		}else{
			holder.buttonDelete.setVisibility(View.VISIBLE);
			holder.buttonDelete.setOnClickListener(new OnClickListener() {
				
				private CategoryDataBean bean = null;

				@Override
				public void onClick(View v) {
					Toast.makeText(mContext, "clicking", Toast.LENGTH_SHORT).show();
					categoryDataBeanList.remove(position);
					notifyDataSetChanged();
				}
			});
		}
		
		
		
		holder.buttonViewCommment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "clicking", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
		
		return view;
	}

	public int getCount() {
		return categoryDataBeanList.size();
	}

	public CategoryDataBean getItem(int position) {
		return categoryDataBeanList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	/**
	 * Filter
	 * @author 9Android.net
	 *
	 */
	public void filter(String charText) {
		charText = charText.toLowerCase();
		categoryDataBeanList.clear();
		if (charText.length() == 0) {
			categoryDataBeanList.addAll(listOrigin);
		} else {
			for (CategoryDataBean categoryDataBean : listOrigin) {
				if (charText.
		            	   equalsIgnoreCase((String) categoryDataBean.getDataName().subSequence(0, charText.length()))) {
					categoryDataBeanList.add(categoryDataBean);
				}
			}
		}
		notifyDataSetChanged();
	}

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}
	
	public void clear(){
	    categoryDataBeanList.clear();
	    notifyDataSetChanged();
	    }
}
