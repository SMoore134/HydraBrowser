package com.sem2458.hydrabrowser;

import android.content.Context;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class WebViewFragment extends Fragment{
	  
	  WebView webView;
	  View v;
	  EditText search;
	  Button b;
	
	
	@Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.webviewfrag, container, false);
		Log.d("Stephen", "Here");
		webView = (WebView) v.findViewById(R.id.webView1);
		initializeWebView();
		
		setupUI();
		webView.loadUrl("http://google.com");
		
		return v;
		
	}
	
	public void initializeWebView(){
		
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url){
				if(url.startsWith("http://"))
					search.setText(url.substring(7));
				if(url.startsWith("https://"))
					search.setText(url);
				
			}
			
			@Override
			public void onReceivedSslError (WebView view,
					SslErrorHandler handler, SslError error){
				//dialoguefragment asking whether the user wants to continue or not
				boolean willContinue = true;
				if(willContinue)
					handler.proceed();
				else
					handler.cancel();
				Log.d("Stephen", "ssl error");
			}
			
		});
		webView.getSettings().setLoadWithOverviewMode(true);
	    webView.getSettings().setUseWideViewPort(true);
	    
	    
	    
	}

	public static Fragment newInstance(String string) {
		WebViewFragment f = new WebViewFragment();
		return f;
	}
	
	private void setupUI(){
		b = (Button)v.findViewById(R.id.cancel);
		search = (EditText) v.findViewById(R.id.search);
		b.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				search.setText("");
			}
		});
		search.setSelectAllOnFocus(true);
		search.setPadding(5, 0, 5, 0);
		search.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus==false){
					if(search.getText().toString().isEmpty())
						search.setText(webView.getUrl().replaceAll("http(s){0,1}://", ""));
				}
			}
		});
		search.setOnEditorActionListener(new OnEditorActionListener(){
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
				if(actionId==EditorInfo.IME_ACTION_GO){
					if(search.getText().toString().contains("http://"))
						webView.loadUrl(search.getText().toString());
					else if(search.getText().toString().contains("https://"))
						webView.loadUrl(search.getText().toString());
					else
						webView.loadUrl("http://"+search.getText().toString());
					InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
	                imm.hideSoftInputFromWindow(search.getWindowToken(), 0);  
					return true;
				}
				return false;
			}
		});
	}

}
