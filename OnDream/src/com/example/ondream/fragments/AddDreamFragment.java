package com.example.ondream.fragments;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.view.MenuItem;
import com.android.volley.Response.Listener;
import com.example.ondream.OnDreamVolley;
import com.example.ondream.R;
import com.example.ondream.activities.ContentActivity;

public class AddDreamFragment extends BaseFragment implements OnClickListener {
	
	private TextView tvContent;
	private Button btnSubmit;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_add_dream, null);
		setHasOptionsMenu(true);
		findViews(view);
		
		return view;
	}
	
	public void findViews(View view) {
		tvContent = (TextView) view.findViewById(R.id.tv_content);
		btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(this);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			getActivity().getSupportFragmentManager().popBackStack();
		}
		return true;
	}

	@Override
	public void onClick(View arg0) {
		String content = tvContent.getText().toString();
		if (arg0 == btnSubmit) {
			OnDreamVolley.getLocalClient().postSendDream(getCurrentUser().getId(), content, "1", new Listener<JSONObject>() {

				@Override
				public void onResponse(JSONObject arg0) {
					Toast.makeText(mContext, "Your dream is successfully posted!", Toast.LENGTH_SHORT).show();
					((ContentActivity) getActivity()).switchContent(new NewsFeedFragment(), false, true, "mContext");
				}
				
			}, getErrorListener());
		}
	}
}
