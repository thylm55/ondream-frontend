package com.example.ondream.fragments;

import java.util.List;

import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.example.ondream.DataParsingController;
import com.example.ondream.OnDreamVolley;
import com.example.ondream.R;
import com.example.ondream.activities.ContentActivity;
import com.example.ondream.models.MDream;
import com.example.ondream.models.MUser;

public class SigninFragment extends BaseFragment implements OnClickListener 
{
	Button btnSignin;
	EditText edtEmail;
	EditText edtPasswd;
	
	String email, password;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_signin, null);
		btnSignin = (Button) view.findViewById(R.id.btn_Signin);
		edtEmail = (EditText) view.findViewById(R.id.edt_Email);
		edtPasswd = (EditText) view.findViewById(R.id.edt_Passwd);
		
		edtEmail.setText("maythywinter@gmail.com");
		edtPasswd.setText("6789");
		
		btnSignin.setOnClickListener(this);
		return view;
	}
	
	@Override
	public void onClick(View v) {
		if (v == btnSignin) {
			email = edtEmail.getText().toString();
			password = edtPasswd.getText().toString();
			OnDreamVolley.getLocalClient().postLogin(email, password, new Listener<JSONObject>() {

				@Override
				public void onResponse(JSONObject arg0) {
					Log.e(TAG, "ok");
					MUser user = DataParsingController.parseUser(arg0);
					if (user != null) {
						setCurrentUser(user);
						((ContentActivity) getActivity()).switchContent(new NewsFeedFragment(), false, true, "mContext");
					} else {
						Toast.makeText(mContext, "Login failed!", Toast.LENGTH_SHORT).show();
					}
				}
			}, getErrorListener());
		}
	}
}