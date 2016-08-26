package com.shareme.model;

import java.io.Serializable;

public class CategoryDataBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dataName;
	private String aboutData;
	private int goodData;
	private int badData;
	private int dataReview;
	
	public CategoryDataBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryDataBean(String dataName, String aboutData, int goodData,
			int badData, int dataReview) {
		super();
		this.dataName = dataName;
		this.aboutData = aboutData;
		this.goodData = goodData;
		this.badData = badData;
		this.dataReview = dataReview;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getAboutData() {
		return aboutData;
	}

	public void setAboutData(String aboutData) {
		this.aboutData = aboutData;
	}

	public int getGoodData() {
		return goodData;
	}

	public void setGoodData(int goodData) {
		this.goodData = goodData;
	}

	public int getBadData() {
		return badData;
	}

	public void setBadData(int badData) {
		this.badData = badData;
	}

	public int getDataReview() {
		return dataReview;
	}

	public void setDataReview(int dataReview) {
		this.dataReview = dataReview;
	}
}
