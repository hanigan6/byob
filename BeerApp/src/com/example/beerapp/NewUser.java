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
import android.widget.Toast;
import android.util.Log;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewUser extends Activity implements OnClickListener {
	private Database dh;
	private EditText userNameEditableField;
	private EditText emailEditableField;
	private EditText passwordEditableField;
	private EditText confirmpasswordEditableField;
	private final static String OPT_NAME = "name";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
	
		userNameEditableField = (EditText) findViewById(R.id.username_text);
		emailEditableField = (EditText) findViewById(R.id.user_email_text);
		passwordEditableField = (EditText) findViewById(R.id.password_text);
		confirmpasswordEditableField = (EditText) findViewById(R.id.confirm_password_text);

		View btnNewUser = (Button) findViewById(R.id.new_user_button);
		btnNewUser.setOnClickListener(this);
	}

	private void adduser() {
		String username = this.userNameEditableField.getText().toString();
		String email = this.emailEditableField.getText().toString();
		String password = this.passwordEditableField.getText().toString();
		String confirmpassword = this.passwordEditableField.getText().toString();
		
		if ((password.equals(confirmpassword)) && (!username.equals(""))
				&& (!password.equals("")) && (!confirmpassword.equals(""))) {
			this.dh = new Database(this);
			this.dh.insertAccount(username, password);
			// this.labResult.setText("Added");
			Toast.makeText(NewUser.this, "new record inserted",
					Toast.LENGTH_SHORT).show();
			finish();
		} else if ((username.equals("")) || (password.equals(""))
				|| (confirmpassword.equals(""))) {
			Toast.makeText(NewUser.this, "Missing entry", Toast.LENGTH_SHORT)
					.show();
		} else if (!password.equals(confirmpassword)) {
			new AlertDialog.Builder(this)
					.setTitle("Error")
					.setMessage("passwords do not match")
					.setNeutralButton("Try Again",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							})

					.show();
		}
		
		startActivity(new Intent(this, MainActivity.class));
	}

	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.new_user_button:
			adduser();
			//startActivity(new Intent(this, MainActivity.class));
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
