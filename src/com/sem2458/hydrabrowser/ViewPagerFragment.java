package com.sem2458.hydrabrowser;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ViewPagerFragment extends Fragment{
	
	ViewPager pager;
	ViewPagerAdapter adapter;
	View v;
	public static List<Fragment> fragments;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.view_pager, container, false);
		fragments = getFragments();
		adapter = new ViewPagerAdapter(getFragmentManager(), fragments);
		pager = (ViewPager)v.findViewById(R.id.pager);
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageSelected(int position) {
				MainActivity.position = position;
				Log.d("Stephen","Position: "+ MainActivity.position);
			}
			
		});
		return v;
	}
	private List<Fragment> getFragments(){
		List<Fragment> fList = new ArrayList<Fragment>();
		fList.add(WebViewFragment.newInstance("Fragment 1"));
		fList.add(WebViewFragment.newInstance("Fragment 2"));
		return fList;
	}
}
