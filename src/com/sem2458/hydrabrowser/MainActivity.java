package com.sem2458.hydrabrowser;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText search;
	View actionbarview;
	int focus = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar actionBar = getActionBar();
		actionbarview = View.inflate(this, R.layout.actionbar_layout, null);
		actionBar.setCustomView(actionbarview);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
		        | ActionBar.DISPLAY_SHOW_HOME);
		search = (EditText) actionbarview.findViewById(R.id.search);
		search.setHorizontallyScrolling(true);
		search.setBackgroundColor(Color.WHITE);
		setupButton();
		
	}

	private void setupButton() {
		Button b = (Button) actionbarview.findViewById(R.id.cancel);
		b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				search.setText("");
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void deleteSearch(View v){
		search.setText("");
	}

}
