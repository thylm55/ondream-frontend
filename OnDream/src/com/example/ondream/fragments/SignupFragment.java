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

import com.android.volley.Response.Listener;
import com.example.ondream.DataParsingController;
import com.example.ondream.OnDreamVolley;
import com.example.ondream.R;
import com.example.ondream.models.MDream;

public class SignupFragment extends BaseFragment implements OnClickListener
{
	
	Button btnSignup;
	EditText edtName;
	EditText edtEmail;
	EditText edtPasswd;
	EditText edtCfrPasswd;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_signup, null);
		btnSignup = (Button) view.findViewById(R.id.btnSignup);
		edtName = (EditText) view.findViewById(R.id.edtName);
		edtEmail = (EditText) view.findViewById(R.id.edtEmail);
		edtPasswd = (EditText) view.findViewById(R.id.edtPasswd);
		edtCfrPasswd =(EditText) view.findViewById(R.id.edtCfrPasswd);
		btnSignup.setOnClickListener(this);
		return view;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnSignup) {
			if(edtPasswd!=edtCfrPasswd)
			{
				Toast.makeText(getActivity(),"Password not correct ",Toast.LENGTH_LONG).show();
			}
			
		}
	}

}