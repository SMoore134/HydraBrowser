package com.sem2458.hydrabrowser;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;

public class MainActivity extends FragmentActivity {
	
	public static int position;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.getActionBar().hide();
		ViewPagerFragment f = new ViewPagerFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.main_activity, f).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onBackPressed(){
		if(position>=0){
			WebViewFragment f = (WebViewFragment) ViewPagerFragment.fragments.get(position);
			if(f.webView.canGoBack()){
				f.webView.goBack();
			}
		}
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) 
	    {
	        super.finish();
	        return true;
	    }
	    return super.onKeyLongPress(keyCode, event);
	}
}
