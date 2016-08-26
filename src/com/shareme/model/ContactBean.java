package com.shareme.model;

import java.io.Serializable;

import android.net.Uri;

public class ContactBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String contactId;
	private String userName;
	private String mobileNum;
	private Uri photoPath;
	
	public ContactBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContactBean(String contactId, String userName, String mobileNum,
			Uri photoPath) {
		super();
		this.contactId = contactId;
		this.userName = userName;
		this.mobileNum = mobileNum;
		this.photoPath = photoPath;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public Uri getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(Uri photoPath) {
		this.photoPath = photoPath;
	}
	
	
}
