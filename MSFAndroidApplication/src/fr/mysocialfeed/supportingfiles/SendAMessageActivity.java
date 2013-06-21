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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
		
    	CheckBox fbBox = (CheckBox) findViewById(R.id.fbChoice);
		CheckBox twBox = (CheckBox) findViewById(R.id.twChoice);
		CheckBox goBox = (CheckBox) findViewById(R.id.goChoice);
		if( fbBox.isChecked() )
			this.hasFb = true;
		else
			this.hasFb = false;
		
		if( twBox.isChecked() )
			this.hasTw = true;
		else
			this.hasTw = false;
		
		if( goBox.isChecked() )
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
		else
			Toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_SHORT).show();

        msgDBSend.close();
        
    	finish();	// We closing this activity.
	}

}
