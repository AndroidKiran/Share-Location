package com.shareme.registration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockActivity;
import com.shareme.R;
import com.shareme.drawer.ShareMeDrawerMainActivity;
import com.shareme.validation.Validation;

public class ConfirmRegistrationActivity extends SherlockActivity implements OnClickListener{
	
	private EditText confirmationEditText;
	private Button confirmButton;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_confirm_layout);
		validateResources();
		
		
	}
	
	private void validateResources(){
		confirmationEditText = (EditText) findViewById(R.id.confirmation_editText);
		confirmationEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isAlphabets(confirmationEditText, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		
		confirmButton = (Button) findViewById(R.id.confirm_button);
		confirmButton.setOnClickListener(this);

	}
	
	private boolean checkValidation() {
        boolean ret = true;
        
        if (!Validation.isAlphabets(confirmationEditText, true)) ret = false;        
        return ret;
    }

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.confirm_button:
			if (checkValidation()) {
				intent = new Intent(ConfirmRegistrationActivity.this, ShareMeDrawerMainActivity.class);
				startActivity(intent);
				finish();
			}
			break;

		default:
			break;
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
