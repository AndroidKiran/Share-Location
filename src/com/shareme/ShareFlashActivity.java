package com.shareme;

import com.shareme.registration.RegistrationActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.widget.ProgressBar;

public class ShareFlashActivity extends Activity {

	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share_flash);
		
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		
		new FlashCall().execute(20);
	}


	
	private class FlashCall extends AsyncTask<Integer, Integer, Void>{
		
		
		
		private Intent intent;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			progressBar.setMax(100);
		}

		@Override
		protected Void doInBackground(Integer... params) {
			int start = params[0];
			
			for (int i = start; i < 100; i+=20) {
				try {
					publishProgress(i);
					SystemClock.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			
			progressBar.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			intent = new Intent(ShareFlashActivity.this, RegistrationActivity.class);
			startActivity(intent);
			finish();
			
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
		case KeyEvent.KEYCODE_BACK :
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	
	
	@Override
    protected void onStart() {
        super.onStart();
    }  
    @Override
    protected void onResume()
    {
        super.onResume();
       
    }  
    
    @Override
    protected void onPause() {
        super.onPause();
        
    }
   @Override
    protected void onStop() {
        super.onStop();
        
    }
   @Override
    protected void onDestroy() 
    {
        super.onDestroy();
        this.finish();
    }


}
