package fr.mysocialfeed.supportingfiles;

import fr.mysocialfeed.models.Messages;
import fr.mysocialfeed.models.MessagesDB;
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
        
        test();
    }
    
    public void onLoginClick(View v) {
        EditText login = (EditText) findViewById(R.id.loginEditText);
        EditText password = (EditText) findViewById(R.id.passwordEditText);

    	if( login.getText().toString().equals("") || password.getText().toString().equals("") ) {
    		Toast.makeText(getApplicationContext(), "Fields need to be not null!", Toast.LENGTH_SHORT).show();
    	}
    	else {
    		Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_SHORT).show();
        	Intent intent = new Intent(LoginDisplayActivity.this, MainActivity.class);
        	
        	intent.putExtra("login", login.getText().toString());
        	intent.putExtra("password", password.getText().toString());
        	startActivity(intent);
        	finish(); // NO (We not closing this activity because the user can back here after this disconnection.)
    	}
    }
    
    public void test() {
        MessagesDB msgDB = new MessagesDB( this ); 
        msgDB.open();

        // Insert a message on the db
        
        Messages msg = new Messages();
        msg.setDate("19-06-2013 15h31");
        msg.setAccountName("Twitter 1");
        msg.setMessage("Premier tweet !");
        msg.setSender("Julien");
        msg.setILike(1);
        msg.setLike(12);
        msg.setType("tw");
        msgDB.insertMessage( msg );
        
        Messages msg2 = new Messages();
        msg2.setDate("19-06-2013 15h40");
        msg2.setAccountName("Twitter 1");
        msg2.setMessage("Deuxième tweet, on est des fous :)");
        msg2.setSender("Julien");
        msg2.setILike(0);
        msg2.setLike(4);
        msg2.setType("tw");
        msgDB.insertMessage( msg2 );
        
        Messages msg3 = new Messages();
        msg3.setDate("19-06-2013 15:46");
        msg3.setAccountName("Twitter 1");
        msg3.setMessage("Arrête de #spam Julien !!");
        msg3.setSender("Bessalel");
        msg3.setILike(1);
        msg3.setLike(4);
        msg3.setType("tw");
        msgDB.insertMessage( msg3 );
        
        Messages msg4 = new Messages();
        msg4.setDate("19-06-2013 15:48");
        msg4.setAccountName("Facebook 1");
        msg4.setMessage("Je suis aussi sur Facebook...");
        msg4.setSender("Julien");
        msg4.setILike(0);
        msg4.setLike(7);
        msg4.setType("fb");
        msgDB.insertMessage( msg4 );
        
        Messages msg5 = new Messages();
        msg5.setDate("19-06-2013 15:49");
        msg5.setAccountName("Google 1");
        msg5.setMessage("Et sur google+ !!!");
        msg5.setSender("Julien");
        msg5.setILike(1);
        msg5.setLike(2);
        msg5.setType("go");
        msgDB.insertMessage( msg5 );
        
        Messages msg6 = new Messages();
        msg6.setDate("19-06-2013 15:51");
        msg6.setAccountName("Twitter 1");
        msg6.setMessage("... mais je préfère Twitter.");
        msg6.setSender("Julien");
        msg6.setILike(0);
        msg6.setLike(3);
        msg6.setType("tw");
        msgDB.insertMessage( msg6 );
        
        //for( int i = 0 ; i < 22 ; i++ )
        //	msgDB.removeMessagesFromId( i );
        
		msgDB.close();
    }
}
