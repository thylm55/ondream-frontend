package com.example.ondream;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class OnDreamVolley {
	private static final String TAG = "OnDreamVolley";
	
	private static RequestQueue mRequestQueue;
	
	private String url;
	
	private static OnDreamVolley localClient;
	private static String localClientUrl = "http://192.168.239.1/api/v1";
	
	private OnDreamVolley() {
		
	}
	
	public static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
	}

	public static RequestQueue getRequestQueue() {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} else {
			throw new IllegalStateException("RequestQueue not initialized");
		}
	}
	
	public String getUrl(String path) {
		return this.url + "/" + path;
	}
	
	public static String generateFullUrl(String url,
			HashMap<String, String> params) {
		if (params.keySet().size() > 0) {
			url = url + "?";
			for (String key : params.keySet()) {
				url = url + key + "=" + params.get(key) + "&";
			}
		}
		Log.e(TAG, "request url: " + url);
		return url;
	}
	
	public static void get(String url, final HashMap<String, String> params,
			Response.Listener<JSONObject> listener,
			Response.ErrorListener errorListener) {
		RequestQueue mRequestQueue = getRequestQueue();
		JsonObjectRequest jsonRequest = new JsonObjectRequest(Method.GET,
				generateFullUrl(url, params), null, listener, errorListener);
		mRequestQueue.add(jsonRequest);
	}

	public static void getArray(String url,
			final HashMap<String, String> params,
			Response.Listener<JSONArray> listener,
			Response.ErrorListener errorListener) {
		RequestQueue mRequestQueue = getRequestQueue();
		JsonArrayRequest jsonRequest = new JsonArrayRequest(generateFullUrl(
				url, params), listener, errorListener);
		mRequestQueue.add(jsonRequest);
	}

	public static void post(String url, JSONObject params,
			Response.Listener<JSONObject> listener,
			Response.ErrorListener errorListener) {
		RequestQueue mRequestQueue = getRequestQueue();
		JsonObjectRequest jsonRequest = new JsonObjectRequest(Method.POST, url,
				params, listener, errorListener);
		mRequestQueue.add(jsonRequest);
		Log.e(TAG, "post url: " + url);
		Log.e(TAG, "params: " + params.toString());
	}
	
	/**
	 * Get khoahoc.com.vn source
	 * @return
	 */
	public static OnDreamVolley getLocalClient() {
		if (localClient == null) {
			localClient = new OnDreamVolley();
			localClient.url = localClientUrl;
		}
		
		return localClient;
	}
	
	public void getListDreams(String userId, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("user_id", userId);
		
		OnDreamVolley.get(getUrl("dreams"), params, listener, errorListener);
	}
	
	public void postLogin(String email, String password,
			Response<JSONObject> listener, Response.ErrorListener errorListener) {
		HashMap<String, String> params = new HashMap<String, String>();
		/*params.put("email", userId);
		params.put("email", userId);*/
	}
}
