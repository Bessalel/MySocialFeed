package fr.mysocialfeed.supportingfiles;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import fr.mysocialfeed.models.Messages;
import fr.mysocialfeed.models.MessagesDB;
import fr.mysocialfeed.models.User;
import android.os.Build;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendAMessageActivity extends Activity {

	private String username;
	private boolean hasFb;
	private boolean hasTw;
	private boolean hasGo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		
		// Recovery data send with the intent method
		Bundle bundle = getIntent().getExtras();
		this.username = bundle.getString("username");
		this.hasFb = false;
		this.hasTw = false;
		this.hasGo = false;
		
		final CheckBox twChoice = (CheckBox) findViewById(R.id.twChoice);
    	final EditText retrieveTextSenT = (EditText) findViewById(R.id.sendEditText);
    	final TextView textLength = (TextView) findViewById(R.id.letters);

    	retrieveTextSenT.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if( retrieveTextSenT.length() > 140 )
					twChoice.setChecked(false);
				
				textLength.setText(""+ retrieveTextSenT.length()+"");
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				if( retrieveTextSenT.length() > 140 )
					twChoice.setChecked(false);
				
				textLength.setText(""+ retrieveTextSenT.length()+"");				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				if( retrieveTextSenT.length() > 140 )
					twChoice.setChecked(false);
				
				textLength.setText(""+ retrieveTextSenT.length()+"");				
			}
    	});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_network, menu);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			ActionBar actionBar = getActionBar();
		    actionBar.setDisplayHomeAsUpEnabled(true);
		}
		return true;	
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:	// click on the app name
			onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onSendClick(View v) {		
		Date time = new Date();
    	EditText retrieveTextSenT = (EditText) findViewById(R.id.sendEditText);
		
    	CheckBox fbChoice = (CheckBox) findViewById(R.id.fbChoice);
		CheckBox twChoice = (CheckBox) findViewById(R.id.twChoice);
		CheckBox goChoice = (CheckBox) findViewById(R.id.goChoice);
		
		if( retrieveTextSenT.length() > 140 )
		{
			twChoice.setChecked(false);
			Toast.makeText(getApplicationContext(), "TWCHOICE false !", Toast.LENGTH_SHORT).show();
		}
		
		if( fbChoice.isChecked() )
			this.hasFb = true;
		else
			this.hasFb = false;
		
		if( twChoice.isChecked() )
			this.hasTw = true;
		else
			this.hasTw = false;
		
		if( goChoice.isChecked() )
			this.hasGo = true;
		else
			this.hasGo = false;
    	
    	MessagesDB msgDBSend = new MessagesDB( this ); 
        msgDBSend.open();
        
		DateFormat shortDateFormatEN = DateFormat.getDateTimeInstance(DateFormat.SHORT,	DateFormat.SHORT, new Locale("EN","en"));
		
		Messages msg = new Messages();
		if( this.hasFb )
		{
	        msg.setDate(shortDateFormatEN.format(time));
	        msg.setAccountName("Facebook");
	        msg.setMessage( retrieveTextSenT.getText().toString() );
	        msg.setSender( this.username );
	        msg.setILike(0);
	        msg.setLike(0);
	        msg.setType("fb");
	        msgDBSend.insertMessage(msg);
	        msg.rebuild();
		}
		if( this.hasTw )
		{
	        msg.setDate(shortDateFormatEN.format(time));
	        msg.setAccountName("Twitter");
	        msg.setMessage( retrieveTextSenT.getText().toString() );
	        msg.setSender( this.username );
	        msg.setILike(0);
	        msg.setLike(0);
	        msg.setType("tw");
	        msgDBSend.insertMessage(msg);
		}
		if( this.hasGo )
		{
	        msg.setDate(shortDateFormatEN.format(time));
	        msg.setAccountName("Google");
	        msg.setMessage( retrieveTextSenT.getText().toString() );
	        msg.setSender( this.username );
	        msg.setILike(0);
	        msg.setLike(0);
	        msg.setType("go");
	        msgDBSend.insertMessage(msg);
		}
        
		// Error, nothing send!
		if( this.hasFb == false && this.hasTw == false && this.hasGo == false )
			Toast.makeText(getApplicationContext(), "Are you sure to send a post? Zero network were selected!", Toast.LENGTH_SHORT).show();
		else {
			Toast.makeText(getApplicationContext(), "Message sent! Please refresh your timeline.", Toast.LENGTH_SHORT).show();
			
			msgDBSend.close();
			
			// Reinitialization
	        fbChoice.setChecked(false);
	        twChoice.setChecked(false);
	        goChoice.setChecked(false);
	        this.hasFb = false;
	        this.hasTw = false;
	        this.hasGo = false;
	        
	    	finish();	// We closing this activity.
		}
		
   	}
}
