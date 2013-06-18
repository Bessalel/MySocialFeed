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
        	//finish(); We not closing this activity because the user can back here after this disconnection.
    	}
    }
    
    public void test() {
        MessagesDB msgDB = new MessagesDB( this ); 
        msgDB.open();

        // Insert a message on the db
        /*
        Messages msg = new Messages();
        msg.setDate("10-06-2013");
        msg.setAccountName("Twitter#1");
        msg.setMessage("Salut à tous, c'est mon premier tweet !");
        msg.setSender("Bessalel");
        msg.setILike(1);
        msg.setLike(12);
        msg.setType("tw");
        msgDB.insertMessage( msg );
        
        Messages msg2 = new Messages();
        msg2.setDate("12-06-2013");
        msg2.setAccountName("Twitter#1");
        msg2.setMessage("Ça c'est mon deuxième tweet lol !");
        msg2.setSender("Bessalel");
        msg2.setILike(0);
        msg2.setLike(4);
        msg2.setType("tw");
        msgDB.insertMessage( msg2 );
        
        Messages msg3 = new Messages();
        msg3.setDate("14-06-2013");
        msg3.setAccountName("Facebook#1");
        msg3.setMessage("Bienvenue sur mon compte Facebook !");
        msg3.setSender("Bessalel");
        msg3.setILike(1);
        msg3.setLike(4);
        msg3.setType("fb");
        msgDB.insertMessage( msg3 );
        
        Messages msg4 = new Messages();
        msg4.setDate("13-06-2013");
        msg4.setAccountName("Twitter#1");
        msg4.setMessage("Salut Bessalel, alors tu aimes bien ?");
        msg4.setSender("Julien");
        msg4.setILike(0);
        msg4.setLike(7);
        msg4.setType("tw");
        msgDB.insertMessage( msg4 );   */
        
        // msgDB.removeMessagesFromId(0);
        
        Messages msgFromDB = msgDB.getMessagesById( 4 ); 
        if( msgFromDB != null ){
        	Toast.makeText(this, "Message trouvé : " + msgFromDB.getMessage(), Toast.LENGTH_SHORT).show();
        }
        else {
        	Toast.makeText(this, "Ce message n'existe pas dans la BDD", Toast.LENGTH_SHORT).show();
        }
        
		msgDB.close();
    }
}
