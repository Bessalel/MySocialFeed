package fr.mysocialfeed.supportingfiles;

import android.os.Build;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		// Recovery data send with the intent method				// TO DO : put them in the user class
		Bundle bundle = getIntent().getExtras();
		this.userLogin = bundle.getString("login");
		
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

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, change the layout view.
		if( tab.getPosition() == 0 ) {
			setContentView(R.layout.filters);
		}
		else if( tab.getPosition() == 1 ) {
			setContentView(R.layout.home);
			TextView login = (TextView) findViewById(R.id.loginText);
			login.setText("Hello " + this.userLogin + "!");
		}
		else if( tab.getPosition() == 2 ) {
			setContentView(R.layout.setting);
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
			// TO DO : déconnecter l'utilisateur !
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
	
	private void sendAMessage() {
		final View addView = getLayoutInflater().inflate(R.layout.send_layout, null);
		new AlertDialog.Builder(this).setTitle("Send a message").setView(addView)
				.setPositiveButton("Post it!", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// TODO : créer la fonction d'envoie sur les réseaux sociaux filtrés !
						// postMessage((TextView) addView.findViewById(R.id.send_message));
						
		            	EditText retrieveTextSenT = (EditText)addView.findViewById(R.id.send_message);
						Toast.makeText(getApplicationContext(), "Data: "+retrieveTextSenT.getText(), Toast.LENGTH_LONG).show();
					}
				}).setNegativeButton("Cancel", null).show();
	}

}
