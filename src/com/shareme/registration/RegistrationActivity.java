package com.shareme.registration;

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
import com.shareme.validation.Validation;



public class RegistrationActivity extends SherlockActivity implements OnClickListener{
	private EditText userNameEditText;
	private EditText userMobileEditText;
	private EditText userPasswordEditText;
	private EditText confirmPasswordEditText;
	private Button submitButton;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_layout);
		
		submitButton = (Button) findViewById(R.id.submit_button);
		submitButton.setOnClickListener(this);
		
		validateResources();
	}
	
	private void validateResources(){
		userNameEditText = (EditText) findViewById(R.id.userName_EditText);
		userNameEditText.addTextChangedListener(new TextWatcher() {
			
            public void afterTextChanged(Editable s) {
                Validation.isAlphabets(userNameEditText, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		
		userMobileEditText = (EditText) findViewById(R.id.userMobile_EditText);
		userMobileEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isNull(userMobileEditText, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		
		userPasswordEditText = (EditText) findViewById(R.id.userPassword_EditText);
		userPasswordEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isAlphabets(userPasswordEditText, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		
		confirmPasswordEditText = (EditText) findViewById(R.id.confirmPassword_EditText);
		confirmPasswordEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isAlphabets(confirmPasswordEditText, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
		
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.submit_button:
			intent = new Intent(RegistrationActivity.this, ConfirmRegistrationActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
		
	}
	
	
	
	private boolean checkValidation() {
        boolean ret = true;
        
        if (!Validation.isAlphabets(userNameEditText, true)) ret = false;    
        if (!Validation.isNull(userMobileEditText, true)) ret = false; 
        if (!Validation.isAlphabets(userPasswordEditText, true)) ret = false; 
        if (!Validation.isAlphabets(confirmPasswordEditText, true)) ret = false; 
        return ret;
    }


	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch(keyCode)
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
