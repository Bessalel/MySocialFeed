package fr.mysocialfeed.models;

import android.support.v4.app.FragmentActivity;
import android.text.Layout;
import android.widget.TextView;
import fr.mysocialfeed.supportingfiles.R;

public class User extends FragmentActivity {
	
	
	
	public void modif( String txt ) {
		TextView txtView = (TextView) findViewById(R.layout.home);
		txtView.setText( txt );
    }
}
