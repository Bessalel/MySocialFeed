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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SendAMessageActivity extends Activity {

	private String username;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		
		// Recovery data send with the intent method
		Bundle bundle = getIntent().getExtras();
		this.username = bundle.getString("username");
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
		
    	MessagesDB msgDBSend = new MessagesDB( this ); 
        msgDBSend.open();
        
		DateFormat shortDateFormatEN = DateFormat.getDateTimeInstance(DateFormat.SHORT,	DateFormat.SHORT, new Locale("EN","en"));
        Messages msg = new Messages();
        msg.setDate(shortDateFormatEN.format(time));
        msg.setAccountName("Twitter 1");
        msg.setMessage( retrieveTextSenT.getText().toString() );
        msg.setSender( this.username );
        msg.setILike(1);
        msg.setLike(1);
        msg.setType("tw");
        
        msgDBSend.insertMessage(msg);
        msgDBSend.close();
	}

}
