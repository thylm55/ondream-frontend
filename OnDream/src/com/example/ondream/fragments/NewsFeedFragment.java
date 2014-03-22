package com.example.ondream.fragments;

import java.util.List;

import android.annotation.SuppressLint;
import android.widget.ListView;

import com.example.ondream.models.MComment;
import com.example.ondream.models.MDream;

@SuppressLint("ValidFragment")
public class NewsFeedFragment extends BaseFragment {

	public static final String TAG = "NewsFeedFragment";
	
	private ListView lvNewsFeed;
	
	private List<MDream> listDreams;
	
	public NewsFeedFragment() {
		// TODO Auto-generated constructor stub
	}
	
	public NewsFeedFragment(MComment user) {
		// TODO Auto-generated constructor stub
	}
	
	
}
