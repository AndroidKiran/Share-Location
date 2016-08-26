package com.shareme.category.fragments;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.shareme.R;
import com.shareme.adpater.CategorySpinnerAdpater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

public class UploadFragment extends SherlockFragment {
	
	private View mUploadView;
	private SherlockFragmentActivity mContext;
	private CategorySpinnerAdpater categorySpinnerAdapter;
	private Spinner categorySpinner;


	public UploadFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        mUploadView = inflater.inflate(R.layout.fragment_upload, container, false);
        
        mContext = getSherlockActivity();
        String[] categoryValues = { "Movies", "Games", "Series","Video", "Audio", };
        
        int category_images[] = { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher};
        
        categorySpinnerAdapter = new CategorySpinnerAdpater(mContext, categoryValues, category_images);
        categorySpinner = (Spinner) mUploadView.findViewById(R.id.categorySpinner);
        categorySpinner.setAdapter(categorySpinnerAdapter);
        
        
        return mUploadView;
    }
}
