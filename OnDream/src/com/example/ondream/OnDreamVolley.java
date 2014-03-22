package com.example.ondream;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
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
	
	public void getListDreams(boolean isNewsFeed, String userId, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("user_id", userId);
		
		if (isNewsFeed) {
			OnDreamVolley.get(getUrl("dreams"), params, listener, errorListener);
		} else {
			OnDreamVolley.get(getUrl("timeline"), params, listener, errorListener);
		}
	}
	
	public void getListSearchDreams(String tag, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("tag", tag);
		
		OnDreamVolley.get(getUrl("find_dreams"), params, listener, errorListener);
	}
	
	public void getListFriends(String userId, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("user_id", userId);
		
		OnDreamVolley.get(getUrl("friends"), params, listener, errorListener);
	}
	
	public void postSayGoodnight(String senderId, String receiverId, String messageType, String content, String pushAt,
			Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		HashMap<String, String> params = new HashMap<String, String>();
		String pushDate = null;
		try {
			pushDate = URLEncoder.encode(pushAt, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		params.put("sender_id", senderId);
		params.put("receiver_id", receiverId);
		params.put("message_type", messageType);
		params.put("content", content);
		params.put("push_at", pushDate);
		
		OnDreamVolley.get(getUrl("new_message"), params, listener, errorListener);
	}
	
	public void postLogin(String email, String password, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		params.put("password", password);
		
		OnDreamVolley.get(getUrl("login"), params, listener, errorListener);
	}
	
	public void postSendDream(String authorId, String content, String privilege,
			Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		String query = null;
		try {
			query = URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("author_id", authorId);
		params.put("content", query);
		params.put("privilege", privilege);
		
		OnDreamVolley.get(getUrl("new_dream"), params, listener, errorListener);
	}
	
	public void postSendMention(String dreamId, String userId,
			Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		JSONObject params = new JSONObject();
		try {
			params.put("dream_id", dreamId);
			params.put("user_id", userId);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OnDreamVolley.post(getUrl("new_mention"), params, listener, errorListener);
	}
	
	public void postSendTag(String dreamId, String tagName,
			Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		JSONObject params = new JSONObject();
		try {
			params.put("dream_id", dreamId);
			params.put("tag_name", tagName);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OnDreamVolley.post(getUrl("new_tag"), params, listener, errorListener);
	}
}
