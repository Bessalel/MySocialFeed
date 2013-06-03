package fr.mysocialfeed.supportingfiles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginDisplayActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
    }
    
    public void onLoginClick(View v) {
		Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(LoginDisplayActivity.this, MainActivity.class);
    	
        EditText login = (EditText) findViewById(R.id.loginEditText);
    	intent.putExtra("login", login.getText().toString());
    	startActivity(intent);
    	//finish(); We not closing this activity because the user can back here after this disconnection.
    }
}
