package com.shareme.adpater;

import java.util.ArrayList;
import java.util.List;

import com.shareme.R;
import com.shareme.model.ContactBean;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyContactAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mInflater;
	private List<ContactBean> contactBeanList = null;
	private ArrayList<ContactBean> listOrigin;

	public MyContactAdapter(Context context, List<ContactBean> contactBeanList) {
		mContext = context;
		this.contactBeanList = contactBeanList;
		mInflater = LayoutInflater.from(mContext);
		this.listOrigin = new ArrayList<ContactBean>();
		this.listOrigin.addAll(contactBeanList);
	}

	public class ViewHolder {		
		ImageView picIcon;
		TextView userNameTextView;
		TextView userMobileTextView;
	}

	public View getView(int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = mInflater.inflate(R.layout.contact_list_item, null);
			holder.picIcon = (ImageView) view.findViewById(R.id.picIcon);
			holder.userNameTextView = (TextView) view.findViewById(R.id.userNameTV);
			holder.userMobileTextView = (TextView) view.findViewById(R.id.userMobileTV);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		if (contactBeanList.get(position).getPhotoPath() != null) {
			holder.picIcon.setImageURI(contactBeanList.get(position).getPhotoPath());
		}else{
			holder.picIcon.setImageResource(R.drawable.ic_launcher);
		}
		
		holder.userNameTextView.setText(contactBeanList.get(position).getUserName());
		holder.userMobileTextView.setText(contactBeanList.get(position).getMobileNum());
		
		return view;
	}

	public int getCount() {
		return contactBeanList.size();
	}

	public ContactBean getItem(int position) {
		return contactBeanList.get(position);
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
		contactBeanList.clear();
		if (charText.length() == 0) {
			contactBeanList.addAll(listOrigin);
		} else {
			for (ContactBean contactBean : listOrigin) {
				if (charText.
		            	   equalsIgnoreCase((String) contactBean.getUserName().subSequence(0, charText.length()))) {
					contactBeanList.add(contactBean);
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
	    contactBeanList.clear();
	    notifyDataSetChanged();
	    }
}
	


