package com.example.ondream;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.ondream.models.MDream;

public class DataParsingController {
	
	public static List<MDream> parseDreams(JSONObject jsonObj) {
		List<MDream> dreams = new ArrayList<MDream>();
		
		JSONArray jsonArray;
		MDream article = new MDream();
		try {
			jsonArray = jsonObj.getJSONArray("dreams");
			
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject json = jsonArray.getJSONObject(i);
					
					article.setId(json.getString("id").toString());
					article.setAuthor(json.getString("author").toString());
					article.setContent(json.getString("content").toString());
					article.setPrivilege(json.getString("privilege").toString());
					article.setCreatedAt(json.getString("created_at").toString());
					
					article.updateTags(json.getJSONArray("tags"));
					article.updateMention(json.getJSONArray("mentions"));
					
					dreams.add(article);
				} catch (JSONException e) {
					dreams.add(article);
					e.printStackTrace();
				}
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return dreams;
	}

}
