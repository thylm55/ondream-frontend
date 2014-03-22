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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.view.MenuItem;
import com.android.volley.Response.Listener;
import com.example.ondream.DataParsingController;
import com.example.ondream.OnDreamVolley;
import com.example.ondream.R;
import com.example.ondream.activities.ContentActivity;
import com.example.ondream.models.MUser;

@SuppressLint({ "ValidFragment", "NewApi" })
public class FriendsFragment extends BaseFragment {

	public static final String TAG = "FriendsFragment";
	
	private ListView lvFriends;
	
	private ListFriendsAdapter adapter;
	
	private List<MUser> listFriends;
	
	public FriendsFragment() {
		listFriends = new ArrayList<MUser>();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_friends, null);
		
		setHasOptionsMenu(true);
		
		getActivity().getActionBar().setTitle("Your friends");
		
		findViews(view);
		
		if (listFriends == null || listFriends.size() == 0) {
			getListFriends(getCurrentUser().getId());
		}
		
		return view;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			getActivity().getSupportFragmentManager().popBackStack();
		}
		return true;
	}
	
	private void getListFriends(String userId) {
		OnDreamVolley.getLocalClient().getListFriends(userId, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject arg0) {
				Log.e(TAG, arg0.toString());
				List<MUser> arrFriends = DataParsingController.parseUsers(arg0);
				Log.e(TAG, arrFriends.get(0).getName());
				
				listFriends.addAll(arrFriends);
				
				adapter.notifyDataSetChanged();
			}
		}, getErrorListener());
	}
	
	private void findViews(View view) {
		lvFriends = (ListView) view.findViewById(R.id.lv_friends);
		adapter = new ListFriendsAdapter(mContext, R.layout.row_friend, listFriends);
		lvFriends.setAdapter(adapter);
		
		lvFriends.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				TimelineFragment mTimeLine = new TimelineFragment(listFriends.get((int) arg3).getId());
				((ContentActivity) getActivity()).switchContent(mTimeLine, true, false, "mContext");
			}
		});
	}
	
	private class ListFriendsAdapter extends ArrayAdapter<MUser> {
		
		private Context context;
		private List<MUser> friends;
		private int resource;

		public ListFriendsAdapter(Context context, int resource,
				List<MUser> objects) {
			super(context, resource, objects);
			
			this.context = context;
			this.friends = objects;
			this.resource = resource;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View row = convertView;
			final NewsFeedHolder holder;
			final MUser user = friends.get(position);
			
			if (row == null) {
				row = LayoutInflater.from(context).inflate(resource, parent, false);
				
				holder = new NewsFeedHolder();
				
				holder.tvName = (TextView) row.findViewById(R.id.tv_title);
				holder.tvStatus = (TextView) row.findViewById(R.id.tv_status);
				holder.tvSayGoodnight = (TextView) row.findViewById(R.id.btnGoodnight);
				
				row.setTag(holder);
			} else {
				holder = (NewsFeedHolder) row.getTag();
			}
			
			holder.tvSayGoodnight.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.e(TAG, "say good night");
					OnDreamVolley.getLocalClient().postSayGoodnight(getCurrentUser().getId(), user.getId(),
							"Night", "default", "2014-03-19 21:59:58", new Listener<JSONObject>() {

								@Override
								public void onResponse(JSONObject arg0) {
									holder.tvSayGoodnight.setBackgroundColor(getResources().getColor(R.color.color_gray));
									holder.tvSayGoodnight.setText("SENT");
								}
							}, getErrorListener());
				}
			});
			
			holder.tvName.setText(user.getName());
			if (user.getStatus().equals("0")) {
				holder.tvStatus.setText("");	
			} else if (user.getStatus().equals("1")){
				holder.tvStatus.setText("SLEEPING");
			} else if (user.getStatus().equals("2")) {
				holder.tvStatus.setText("SLEEPLESS");
			}
			
			return row;
		}
		
		
		public class NewsFeedHolder {
			public TextView tvName;
			public TextView tvStatus;
			public TextView tvSayGoodnight;
		}
		
	}
}
