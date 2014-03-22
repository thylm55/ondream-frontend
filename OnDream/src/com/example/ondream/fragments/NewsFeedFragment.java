package com.example.ondream.fragments;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ondream.R;
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news_feed, null);
		
		findViews(view);
		
		return view;
	}
	
	private void findViews(View view) {
		lvNewsFeed = (ListView) view.findViewById(R.id.lv_news);
	}
	
	private class NewsFeedAdapter extends ArrayAdapter<MDream> {
		
		private Context context;
		private List<MDream> articles;
		private int resource;

		public NewsFeedAdapter(Context context, int resource,
				List<MDream> objects) {
			super(context, resource, objects);
			
			this.context = context;
			this.articles = objects;
			this.resource = resource;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return super.getView(position, convertView, parent);
		}
		
		
	}
}
