package fr.mysocialfeed.supportingfiles;

import junit.framework.Assert;
import oauth.signpost.OAuth;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class AddNetworkTwitterActivity extends Activity {

	private String typeAccount;
	private int userId;
	
	private String CONSUMER_KEY = "cARhnMxZ6HHZsdNRzI1SWQ";
	private String CONSUMER_SECRET = "UZloxdl2NtxLLAzDXZ2kRpPsFYkRNWQruyllVEFIXi0";
	private String CALLBACK_URL = "http://google.fr";
	
	private CommonsHttpOAuthConsumer mConsumer;
	private OAuthProvider mProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_network_twitter);

		// Recovery data send with the intent method
		Bundle bundle = getIntent().getExtras();
		this.typeAccount = bundle.getString("typeAccount");
		this.userId = bundle.getInt("userId");
		
		mConsumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		OAuthProvider mProvider = new CommonsHttpOAuthProvider("http://twitter.com/oauth/request_token", "http://twitter.com/oauth/access_token", "http://twitter.com/oauth/authorize");
		mProvider.setOAuth10a(true);
		
		try {
			String authUrl = mProvider.retrieveRequestToken(mConsumer, CALLBACK_URL);
			WebView mWebView = (WebView) findViewById(R.id.browserView);
			mWebView.setWebViewClient(new WebViewClient() {
				@Override
				public void onPageStarted(WebView view, String url, Bitmap favicon) {
				    if (url.startsWith(CALLBACK_URL)) {
				        Intent data = new Intent();
				        data.setData(Uri.parse(url));
				        setResult(RESULT_OK, data);
				        finish();
				    }
				}
			});
		} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthNotAuthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode == RESULT_OK) {
	        Uri uri = data.getData(); String authToken = uri.getQueryParameter(OAuth.OAUTH_TOKEN);
	        String authVerifier = uri.getQueryParameter(OAuth.OAUTH_VERIFIER);
	        Assert.assertEquals(authToken, mConsumer.getToken());

	        try {
		        // Both elements are now accessible by getToken() and getTokenSecret() method.
				mProvider.retrieveAccessToken(mConsumer, authVerifier);
			} catch (OAuthMessageSignerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OAuthNotAuthorizedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OAuthExpectationFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OAuthCommunicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
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

}
