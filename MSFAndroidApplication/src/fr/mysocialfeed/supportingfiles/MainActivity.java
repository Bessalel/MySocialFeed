package fr.mysocialfeed.supportingfiles;

import java.util.HashMap;
import java.util.Map;
import java.util.Locale;
import java.util.Date;
import java.text.DateFormat;

import fr.mysocialfeed.models.AccountDB;
import fr.mysocialfeed.models.Messages;
import fr.mysocialfeed.models.MessagesDB;
import fr.mysocialfeed.models.User;
import android.os.Build;
import android.os.Bundle;
import android.accounts.Account;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/***
 * @author Julien Bernard
 * @date May 2013 
 * Display the centralized data on three pages (slides).
 * 3 layout:
 * - Filters, the user can filter data to display
 * - Home, timeline
 * - Setting, add or modify an social account and logout.
 */
public class MainActivity extends FragmentActivity implements TabListener {

	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private String userLogin;
	private User usr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		// Recovery data send with the intent method
		Bundle bundle = getIntent().getExtras();
		this.userLogin = bundle.getString("login");
		
		// TO DO :  RETRIEVE DATA FROM SERVER DB
		// Now, put some dummy data for test ...
		usr = new User( 1, this.userLogin, "Name", "LastName", "Email", 0, 0, 0);
		
		// Set up the action bar to show tabs.
	    final ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    // For each of the sections in the app, add a tab to the action bar.
	    actionBar.addTab(actionBar.newTab().setText(R.string.menu_filter)
	        .setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText(R.string.menu_home)
	        .setTabListener(this));
	    actionBar.addTab(actionBar.newTab().setText(R.string.menu_setting)
	        .setTabListener(this));
	}
	
	/*************************************************************
	 * Tab navigation, using fragment with actionbar. Add a new fragment (new page) with newTab() method.
	 * Using 3 activities : filters, home and settings.
	 */
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current tab position.
	    if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
	    	getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
	    }
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current tab position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
	    		.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			ActionBar actionBar = getActionBar();
		    actionBar.setDisplayHomeAsUpEnabled(true);
		}
		return true;
	}

	@SuppressLint("UseSparseArrays")	// For better performance (HashMap)
	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, change the layout view.
		if( tab.getPosition() == 0 ) {
			setContentView(R.layout.filters);
			CheckBox fbBox = (CheckBox) findViewById(R.id.checkBox1);
			CheckBox twBox = (CheckBox) findViewById(R.id.checkBox2);
			CheckBox goBox = (CheckBox) findViewById(R.id.checkBox3);
			if( usr.get_hasFacebookFilter() )
				fbBox.setChecked( true );
			if( usr.get_hasTwitterFilter() )
				twBox.setChecked( true );
			if( usr.get_hasGoogleFilter() )
				goBox.setChecked( true );
		}
		else if( tab.getPosition() == 1 ) {
			setContentView(R.layout.home);
			TextView login = (TextView) findViewById(R.id.loginText);
			login.setText("Your TimeLine, " + this.userLogin + "!");
			
			MessagesDB msgDB = new MessagesDB( this ); 
	        msgDB.open();
			
			Map<Integer, Messages> arrayMap = new HashMap<Integer, Messages>();
			if( usr.get_hasFacebookFilter() )
				msgDB.getMessagesFromType(arrayMap, "fb");
			if( usr.get_hasTwitterFilter() )
				msgDB.getMessagesFromType(arrayMap, "tw");
			if( usr.get_hasGoogleFilter() )
				msgDB.getMessagesFromType(arrayMap, "go");

			TextView zoneText = (TextView) findViewById(R.id.textZoneHome);
			
	        if( arrayMap.size() != 0 ){
	        	String s = "";
	        	for( int i = 0 ; i < arrayMap.size() ; i++ ) {
        			s += "<br/><b>[" + arrayMap.get(i).getType() + "] Post send by " + arrayMap.get(i).getSender() + " at " + arrayMap.get(i).getDate() + "</b><br/>" + arrayMap.get(i).getMessage() + "<br/>";
	        	}
        		zoneText.setText( Html.fromHtml(s) );
	        }
	        
	        if( arrayMap.size() != 0 ) {
				Toast.makeText(getApplicationContext(), "Size map : " + arrayMap.size(), Toast.LENGTH_SHORT).show();	
	        }
	        
	        msgDB.close();
		}
		else if( tab.getPosition() == 2 ) {
			setContentView(R.layout.setting);
			AccountDB accountDB = new AccountDB( this );
			accountDB.open();
			TextView fbAccount = (TextView) findViewById(R.id.textViewFb);
			TextView twAccount = (TextView) findViewById(R.id.TextViewTw);
			TextView goAccount = (TextView) findViewById(R.id.TextViewGo);
			fbAccount.setText( "" + accountDB.countByAccountType( "fb" ) + "" );
			twAccount.setText( "" + accountDB.countByAccountType( "tw" ) + "" );
			goAccount.setText( "" + accountDB.countByAccountType( "go" ) + "");
			
			Toast.makeText(getApplicationContext(), "Tw count: " + accountDB.countByAccountType( "tw" ), Toast.LENGTH_SHORT).show();
			accountDB.close();
		}
		else { // Error!
			Intent intent = new Intent(MainActivity.this, ErrorActivity.class);
	    	startActivity(intent);
		}
	}
	
	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}
	
	/**
	 * We have 3 sections so 3 fragments.
	 */
	public static class PageFilters extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {	
        	return inflater.inflate(R.layout.filters, container, false);
        }
	}
	
	public static class PageHome extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    		return inflater.inflate(R.layout.home, container, false);
        }
	}
	
	public static class PageSetting extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
   			return inflater.inflate(R.layout.setting, container, false);
        }
	}
	  
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:	// click on the app name
			
			// Calling the garbage collector to be sure user object will be destroyed!
			usr = null;
			Runtime r = Runtime.getRuntime();
			r.gc();
			
			Toast.makeText(getApplicationContext(), "Logout successful, bye!", Toast.LENGTH_SHORT).show();
			onBackPressed();
			return true;
		case R.id.menu_refresh:
			Toast.makeText(getApplicationContext(), "Refresh, please wait a moment...", Toast.LENGTH_LONG).show();
			return true;
		case R.id.menu_send:
			sendAMessage();			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void onLogoutClick(View v) {
		Toast.makeText(getApplicationContext(), "Logout successful, bye!", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this, LoginDisplayActivity.class);
    	startActivity(intent);
    	finish();	// We closing this activity.
    }

	public void saveFiltersButtonClick(View v) {
		Toast.makeText(getApplicationContext(), "Saving filters...", Toast.LENGTH_SHORT).show();
		
		CheckBox fbBox = (CheckBox) findViewById(R.id.checkBox1);
		CheckBox twBox = (CheckBox) findViewById(R.id.checkBox2);
		CheckBox goBox = (CheckBox) findViewById(R.id.checkBox3);
		if( fbBox.isChecked() )
			usr.set_hasFacebookFilter( true );
		else
			usr.set_hasFacebookFilter( false );
		
		if( twBox.isChecked() )
			usr.set_hasTwitterFilter( true );
		else
			usr.set_hasTwitterFilter( false );
		
		if( goBox.isChecked() )
			usr.set_hasGoogleFilter( true );
		else
			usr.set_hasGoogleFilter( false );
	}
	
	public void onAddFbClick(View v) {
    	Intent intent = new Intent(MainActivity.this, AddNetworkActivity.class);
    	intent.putExtra("typeAccount", "fb");
    	intent.putExtra("userId", usr.get_userID());
    	startActivity(intent);
    }
	public void onAddTwClick(View v) {
    	Intent intent = new Intent(MainActivity.this, AddNetworkActivity.class);
    	intent.putExtra("typeAccount", "tw");
    	intent.putExtra("userId", usr.get_userID());
    	startActivity(intent);
    }
	public void onAddGoClick(View v) {
    	Intent intent = new Intent(MainActivity.this, AddNetworkActivity.class);
    	intent.putExtra("typeAccount", "go");
    	intent.putExtra("userId", usr.get_userID());
    	startActivity(intent);
    }
	
	public void sendAMessage() {
		Intent intent = new Intent(MainActivity.this, SendAMessageActivity.class);
    	intent.putExtra("username", usr.get_username());
    	startActivity(intent);
	}
}
