package com.example.dbhandler;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "VidalDB";
 
    // Contacts table name
    private static final String TABLE_PIN = "registration";
    private static final String TABLE_TIP = "tip";
    
    // Contacts Table Columns names
    private static final String KEY_PIN_ID = "pin_id";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_TTK_ID = "ttkid";
    private static final String KEY_POLICY_NUM = "policy_num";
    private static final String KEY_IMEI_NUM = "imei_num";
    private static final String KEY_PIN = "pin";
    
    private static final String KEY_TIP_ID = "tip_id";
    private static final String KEY_TITLE = "tip_title";
    private static final String KEY_COLOR = "tip_color";
    private static final String KEY_CONTENT = "tip_content";
    private static final String KEY_PATH = "tip_path";
    private static final String KEY_DATE = "tip_DATE";
    
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REGISTARION_TABLE = "CREATE TABLE " + TABLE_PIN + "("
        		+ KEY_PIN_ID + "INTEGER PRIMARY KEY,"
        		+ KEY_QUESTION + " TEXT,"
        		+ KEY_ANSWER + " TEXT,"
        		+ KEY_TTK_ID + " TEXT,"
        		+ KEY_POLICY_NUM + " TEXT,"
        		+ KEY_IMEI_NUM + " TEXT,"
        		+ KEY_PIN + " TEXT"
        		+")";
        db.execSQL(CREATE_REGISTARION_TABLE);
        
        String CREATE_TIP_TABLE = "CREATE TABLE " + TABLE_TIP + "("
        		+ KEY_TIP_ID + "INTEGER PRIMARY KEY,"
        		+ KEY_TITLE + " TEXT,"
        		+ KEY_COLOR + " TEXT,"
        		+ KEY_CONTENT + " TEXT,"
        		+ KEY_PATH + " TEXT,"
        		+ KEY_DATE + " TEXT"
        		+")";
        db.execSQL(CREATE_TIP_TABLE);
        
        
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIP);
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     * 
     */
    /*public void deletePinBean(){
    	SQLiteDatabase db = this.getReadableDatabase();
    	db.delete(TABLE_PIN, null, null);
        db.close();
	}
	
	public String savePinBean(PinBean pinBean){
		
		String saveMsg = "success";
		long insertValue = 0;
		
		SQLiteDatabase myDataBase = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_QUESTION, pinBean.getQuestion());
		contentValues.put(KEY_ANSWER, pinBean.getAnswer());
		contentValues.put(KEY_TTK_ID, pinBean.getTtkid());
		contentValues.put(KEY_POLICY_NUM, pinBean.getPolicy_num());
		contentValues.put(KEY_IMEI_NUM, pinBean.getImei_num());
		contentValues.put(KEY_PIN, pinBean.getPin());
		
		insertValue = myDataBase.insert(TABLE_PIN, null, contentValues);
		
		if (insertValue == 0) {
			saveMsg = "fail";
		}
		
		contentValues = null;
		myDataBase.close();
		return saveMsg;
		
	}
	
	public PinBean fetchPinBean() {
        
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PIN;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        PinBean bean = null;
		if (cursor != null) {
			if(cursor.moveToFirst()){
				try{
		   			bean  = new PinBean(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
		   		}catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				bean = new PinBean("NA","NA","NA","NA","NA","NA");
			}
		}
   		
		cursor.close();
		db.close();
		return bean;
        
       
    }
	
	 public List<TipBean> getTipBeans() {
	        List<TipBean> TipBeanList = new ArrayList<TipBean>();
	        
	        // Select All Query
	        String selectQuery = "SELECT  * FROM " + TABLE_TIP;
	 
	        SQLiteDatabase db = this.getWritableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	 
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	                TipBean tipBean = new TipBean();
	                try{
		                tipBean.setTitle(cursor.getString(1));
		                tipBean.setColor(cursor.getString(2));
		                tipBean.setHealthTip(cursor.getString(3));
		                tipBean.setTipPath(cursor.getString(4));
		                tipBean.setTipDate(cursor.getString(5));
	                }catch (Exception e) {
						// TODO: handle exception
	                	e.printStackTrace();
					}
	                
	                // Adding contact to list
	                TipBeanList.add(tipBean);
	            } while (cursor.moveToNext());
	        }
	        cursor.close();
	        db.close();
	        // return contact list
	        return TipBeanList;
	    }
	 
	 public String saveTipBean(TipBean tipBean){
			
			String saveMsg = "success";
			long insertValue = 0;
			ContentValues contentValues = null;
			
			String selectQuery = "SELECT  * FROM " + TABLE_TIP +" WHERE "+ KEY_DATE +" = '"+tipBean.getTipDate()+"'";
			
			SQLiteDatabase myDataBase = this.getWritableDatabase();
			
	        Cursor cursor = myDataBase.rawQuery(selectQuery, null);
	        
	        if (!cursor.moveToFirst()){
	        	contentValues = new ContentValues();
	        	
				contentValues.put(KEY_TITLE, tipBean.getTitle());
				contentValues.put(KEY_COLOR, tipBean.getColor());
				contentValues.put(KEY_CONTENT, tipBean.getHealthTip());
				contentValues.put(KEY_PATH, tipBean.getTipPath());
				contentValues.put(KEY_DATE, tipBean.getTipDate());
				
				insertValue = myDataBase.insert(TABLE_TIP, null, contentValues);
	        }
			
			
			if (insertValue == 0) {
				saveMsg = "fail";
			}
			
			cursor.close();
			myDataBase.close();
			return saveMsg;
			
		}
	 
	 
	 public String deleteTip(TipBean tipBean) {
		 String deleteMsg = "success";
		 int deleteStat = 0;
	     SQLiteDatabase db = this.getWritableDatabase();
	        
	     deleteStat = db.delete(TABLE_TIP, KEY_DATE + " = ?",new String[] { String.valueOf(tipBean.getTipDate())});
	     
	     if (deleteStat == 0 ) {
				deleteMsg = "fail";
	     } 
	        
	     db.close();
	     return deleteMsg;
	 }
	*/
    
}