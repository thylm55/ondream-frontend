package com.example.ondream.fragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.android.volley.Response.Listener;
import com.example.ondream.DataParsingController;
import com.example.ondream.OnDreamVolley;
import com.example.ondream.R;
import com.example.ondream.activities.ContentActivity;
import com.example.ondream.models.MDream;

@SuppressLint("ValidFragment")
public class TimelineFragment extends BaseFragment {

	public static final String TAG = "NewsFeedFragment";
	
	private ListView lvNewsFeed;
	
	private NewsFeedAdapter adapter;
	
	private List<MDream> listDreams;
	
	private String friendId;
	
	public TimelineFragment() {
		listDreams = new ArrayList<MDream>();	
	}
	
	public TimelineFragment(String friendId) {
		listDreams = new ArrayList<MDream>();	
		this.friendId = friendId;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news_feed, null);
		
		setHasOptionsMenu(true);
		
		findViews(view);
		
		if (friendId != null) {
			getListDreams(friendId);
		} else {
			getListDreams(getCurrentUser().getId());	
		}
		
		return view;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		
		getSherlockActivity().getSupportMenuInflater().inflate(R.menu.main, menu);
		menu.add(Menu.NONE, 3, 1, "New Dream")
	    	.setIcon(R.drawable.ic_new_dream_small)
	    	.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			getActivity().getSupportFragmentManager().popBackStack();
		} else if (item.getItemId() == 3) {
			((ContentActivity) getActivity()).switchContent(new AddDreamFragment(), false, true, "mContext");
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void getListDreams(String userId) {
		OnDreamVolley.getLocalClient().getListDreams(false, userId, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject arg0) {
				Log.e(TAG, arg0.toString());
				List<MDream> arrDreams = DataParsingController.parseDreams(arg0);
				
				listDreams.addAll(arrDreams);
				
				adapter.notifyDataSetChanged();
			}
		}, getErrorListener());
	}
	
	private void findViews(View view) {
		lvNewsFeed = (ListView) view.findViewById(R.id.lv_news);
		adapter = new NewsFeedAdapter(mContext, R.layout.row_dream, listDreams);
		lvNewsFeed.setAdapter(adapter);
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
			
			View row = convertView;
			NewsFeedHolder holder;
			MDream dream = articles.get(position);
			
			if (row == null) {
				row = LayoutInflater.from(context).inflate(resource, parent, false);
				
				holder = new NewsFeedHolder();
				
				holder.tvAuthor = (TextView) row.findViewById(R.id.tv_name);
				holder.tvContent = (TextView) row.findViewById(R.id.tv_content);
				holder.tvCommentIt = (TextView) row.findViewById(R.id.tv_comment_it);
				holder.tvTags = (TextView) row.findViewById(R.id.tv_tags);
				holder.tvMentions = (TextView) row.findViewById(R.id.tv_mention);
				holder.tvCommentIt.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Log.e(TAG, "onClick");
					}
				});
				
				row.setTag(holder);
			} else {
				holder = (NewsFeedHolder) row.getTag();
			}
			
			holder.tvAuthor.setText(dream.getAuthor());
			holder.tvContent.setText(dream.getContent());
			
			return row;
		}
		
		
		public class NewsFeedHolder {
			public TextView tvAuthor;
			public TextView tvContent;
			public TextView tvCommentIt;
			public TextView tvTags;
			public TextView tvMentions;
		}
		
	}
}
