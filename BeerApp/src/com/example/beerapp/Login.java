package com.example.beerapp;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

public class Login extends Activity implements OnClickListener {
	private EditText userNameEditableField;
	private EditText passwordEditableField;
	private final static String OPT_NAME = "name";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		for (int x = 0; x < 10000; x++) {
			
		}
	
		userNameEditableField = (EditText) findViewById(R.id.username_text);
		passwordEditableField = (EditText) findViewById(R.id.password_text);
		View btnLogin = (Button) findViewById(R.id.login_button);
		btnLogin.setOnClickListener(this);
		View btnCancel = (Button) findViewById(R.id.cancel_button);
		btnCancel.setOnClickListener(this);
		View btnNewUser = (Button) findViewById(R.id.new_user_button);
		btnNewUser.setOnClickListener(this);
	}

	private void checkLogin() {
		String username = this.userNameEditableField.getText().toString();
		String password = this.passwordEditableField.getText().toString();
		startActivity(new Intent(this, MainActivity.class));
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_button:
			checkLogin();
			break;
		case R.id.cancel_button:
			finish();
			break;
		case R.id.new_user_button:
			startActivity(new Intent(this, MainActivity.class));
			break;
		}
	}
	
	protected void onResume() {
		super.onResume();
		
		Log.i("x", "resume");
	}
	
	protected void onPause() {
		super.onPause();
		
		Log.i("x", "pause");
	}
	
	protected void onStart() {
		super.onStart();
		
		Log.i("x", "start");
	}
	
	protected void onStop() {
		super.onStop();
		
		Log.i("x", "stop");
	}
}
