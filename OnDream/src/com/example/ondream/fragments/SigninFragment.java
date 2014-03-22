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

import com.android.volley.Response.Listener;
import com.example.ondream.DataParsingController;
import com.example.ondream.OnDreamVolley;
import com.example.ondream.R;
import com.example.ondream.models.MDream;

public class SigninFragment extends BaseFragment implements OnClickListener 
{
	Button btnSignin;
	EditText edtEmail;
	EditText edtPasswd;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_signin, null);
		btnSignin = (Button) view.findViewById(R.id.btn_Signin);
		edtEmail = (EditText) view.findViewById(R.id.edt_Email);
		edtPasswd = (EditText) view.findViewById(R.id.edt_Passwd);
		btnSignin.setOnClickListener(this);
		return view;
	}
	
	@Override
	public void onClick(View v) {
		if (v == btnSignin) {
			OnDreamVolley.getLocalClient().postLogin(edtEmail.getText().toString(), edtPasswd.getText().toString(), new Listener<JSONObject>() {

				@Override
				public void onResponse(JSONObject arg0) {
					
				}
			}, getErrorListener());
		}
	}
}