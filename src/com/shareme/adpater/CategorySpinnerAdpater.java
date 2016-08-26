package com.shareme.adpater;

import com.shareme.R;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CategorySpinnerAdpater extends BaseAdapter{
	
	private String[] categoryNames;
	private int[] categoryImages;
	private Context context;

	public CategorySpinnerAdpater(Context context,String[] categoryNames,int[] categoryImages) {
		this.categoryNames = categoryNames;
		this.categoryImages = categoryImages;
		this.context = context;
		
	}

	@Override
	public int getCount() {
		return categoryNames.length;
	}

	@Override
	public String getItem(int index) {
		String name = categoryNames[index];
		return name;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public class ViewHolder {		
		TextView categoryNameTV;
		ImageView categoryImage;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) { 
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
        	LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_list_item, null);
            holder.categoryNameTV = (TextView) convertView.findViewById(R.id.textView_CategoryName);
            holder.categoryImage = (ImageView) convertView.findViewById(R.id.imageView_CategoryPic);
            convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
        
		holder.categoryNameTV.setText(categoryNames[position]);
		holder.categoryImage.setImageResource(categoryImages[position]);
		
        return convertView;
	}
	

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
        	LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_list_item, null);
            holder.categoryNameTV = (TextView) convertView.findViewById(R.id.textView_CategoryName);
            holder.categoryImage = (ImageView) convertView.findViewById(R.id.imageView_CategoryPic);
            convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
        
		holder.categoryNameTV.setText(categoryNames[position]);
		holder.categoryImage.setImageResource(categoryImages[position]);
		
        return convertView;
	}
	
}


